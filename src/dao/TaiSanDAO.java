package dao;

import model.TaiSan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiSanDAO {
    private Connection connection;
    
    public TaiSanDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<TaiSan> getAllTaiSan() throws SQLException {
        List<TaiSan> list = new ArrayList<>();
        String sql = "SELECT * FROM taisan";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TaiSan ts = new TaiSan(
                    rs.getInt("Mats"),
                    rs.getString("Tents"),
                    rs.getInt("Soluong"),
                    rs.getString("Tinhtrang"),
                    rs.getString("Maphong")
                );
                list.add(ts);
            }
        }
        return list;
    }
    
    // Thêm tài sản - ID tự tăng
    public boolean themTaiSan(TaiSan ts) throws SQLException {
        String sql = "INSERT INTO taisan (Tents, Soluong, Tinhtrang, Maphong) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, ts.getTents());
            pstmt.setInt(2, ts.getSoluong());
            pstmt.setString(3, ts.getTinhtrang());
            
            if (ts.getMaphong() != null && !ts.getMaphong().isEmpty()) {
                pstmt.setString(4, ts.getMaphong());
            } else {
                pstmt.setNull(4, Types.VARCHAR);
            }
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                // Lấy ID tự tăng vừa được tạo
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ts.setMats(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }
    
    public boolean suaTaiSan(TaiSan ts) throws SQLException {
        String sql = "UPDATE taisan SET Tents = ?, Soluong = ?, Tinhtrang = ?, Maphong = ? WHERE Mats = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, ts.getTents());
            pstmt.setInt(2, ts.getSoluong());
            pstmt.setString(3, ts.getTinhtrang());
            
            if (ts.getMaphong() != null && !ts.getMaphong().isEmpty()) {
                pstmt.setString(4, ts.getMaphong());
            } else {
                pstmt.setNull(4, Types.VARCHAR);
            }
            
            pstmt.setInt(5, ts.getMats());
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean xoaTaiSan(int mats) throws SQLException {
        String sql = "DELETE FROM taisan WHERE Mats = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, mats);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public List<TaiSan> timKiemTaiSan(String keyword) throws SQLException {
        List<TaiSan> list = new ArrayList<>();
        String sql = "SELECT * FROM taisan WHERE Mats LIKE ? OR Tents LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TaiSan ts = new TaiSan(
                        rs.getInt("Mats"),
                        rs.getString("Tents"),
                        rs.getInt("Soluong"),
                        rs.getString("Tinhtrang"),
                        rs.getString("Maphong")
                    );
                    list.add(ts);
                }
            }
        }
        return list;
    }
}
