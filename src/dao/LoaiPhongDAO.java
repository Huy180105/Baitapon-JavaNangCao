package dao;

import model.LoaiPhong;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDAO {
    private Connection connection;
    
    public LoaiPhongDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<LoaiPhong> getAllLoaiPhong() throws SQLException {
        List<LoaiPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM loaiphong";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LoaiPhong lp = new LoaiPhong(
                    rs.getInt("Maloaiphong"),
                    rs.getString("Tenloai"),
                    rs.getDouble("Dongia")
                );
                list.add(lp);
            }
        }
        return list;
    }
    
    // Thêm loại phòng - ID tự tăng
    public boolean themLoaiPhong(LoaiPhong lp) throws SQLException {
        String sql = "INSERT INTO loaiphong (Tenloai, Dongia) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, lp.getTenloai());
            pstmt.setDouble(2, lp.getDongia());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                // Lấy ID tự tăng vừa được tạo
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lp.setMaloaiphong(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }
    
    public boolean suaLoaiPhong(LoaiPhong lp) throws SQLException {
        String sql = "UPDATE loaiphong SET Tenloai = ?, Dongia = ? WHERE Maloaiphong = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, lp.getTenloai());
            pstmt.setDouble(2, lp.getDongia());
            pstmt.setInt(3, lp.getMaloaiphong());
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean xoaLoaiPhong(int maloaiphong) throws SQLException {
        String sql = "DELETE FROM loaiphong WHERE Maloaiphong = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, maloaiphong);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public List<LoaiPhong> timKiemLoaiPhong(String keyword) throws SQLException {
        List<LoaiPhong> list = new ArrayList<>();
        String sql = "SELECT * FROM loaiphong WHERE Maloaiphong LIKE ? OR Tenloai LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LoaiPhong lp = new LoaiPhong(
                        rs.getInt("Maloaiphong"),
                        rs.getString("Tenloai"),
                        rs.getDouble("Dongia")
                    );
                    list.add(lp);
                }
            }
        }
        return list;
    }
}