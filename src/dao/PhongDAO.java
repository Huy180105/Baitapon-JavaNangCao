package dao;

import database.DBConnection;
import model.Phong;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongDAO {
    private Connection connection;
    
    public PhongDAO(Connection connection) {
        this.connection = connection;
    }

    public PhongDAO() {
    }
    
    public List<Phong> getAllPhong() throws SQLException {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM phongtro";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Integer maloaiphong = rs.getInt("Maloaiphong");
                if (rs.wasNull()) maloaiphong = null;
                
                Integer makhach = rs.getInt("Makhach");
                if (rs.wasNull()) makhach = null;
                
                Integer mats = rs.getInt("Mats");
                if (rs.wasNull()) mats = null;
                
                Phong p = new Phong(
                    rs.getString("Maphong"),
                    rs.getString("Tenphong"),
                    rs.getInt("Dientich"),
                    rs.getString("Trangthai"),
                    maloaiphong,
                    makhach,
                    mats
                );
                list.add(p);
            }
        }
        return list;
    }
    
    public boolean themPhong(Phong p) throws SQLException {
        String sql = "INSERT INTO phongtro (Maphong, Tenphong, Dientich, Trangthai, Maloaiphong, Makhach, Mats) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, p.getMaphong());
            pstmt.setString(2, p.getTenphong());
            pstmt.setInt(3, p.getDientich());
            pstmt.setString(4, p.getTrangthai());
            
            if (p.getMaloaiphong() != null) {
                pstmt.setInt(5, p.getMaloaiphong());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            
            if (p.getMakhach() != null) {
                pstmt.setInt(6, p.getMakhach());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            
            if (p.getMats() != null) {
                pstmt.setInt(7, p.getMats());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean suaPhong(Phong p) throws SQLException {
        String sql = "UPDATE phongtro SET Tenphong = ?, Dientich = ?, Trangthai = ?, Maloaiphong = ?, Makhach = ?, Mats = ? WHERE Maphong = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, p.getTenphong());
            pstmt.setInt(2, p.getDientich());
            pstmt.setString(3, p.getTrangthai());
            
            if (p.getMaloaiphong() != null) {
                pstmt.setInt(4, p.getMaloaiphong());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            
            if (p.getMakhach() != null) {
                pstmt.setInt(5, p.getMakhach());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            
            if (p.getMats() != null) {
                pstmt.setInt(6, p.getMats());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            
            pstmt.setString(7, p.getMaphong());
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean xoaPhong(String maphong) throws SQLException {
        String sql = "DELETE FROM phongtro WHERE Maphong = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, maphong);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public List<Phong> timKiemPhong(String keyword) throws SQLException {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM phongtro WHERE Maphong LIKE ? OR Tenphong LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Integer maloaiphong = rs.getInt("Maloaiphong");
                    if (rs.wasNull()) maloaiphong = null;
                    
                    Integer makhach = rs.getInt("Makhach");
                    if (rs.wasNull()) makhach = null;
                    
                    Integer mats = rs.getInt("Mats");
                    if (rs.wasNull()) mats = null;
                    
                    Phong p = new Phong(
                        rs.getString("Maphong"),
                        rs.getString("Tenphong"),
                        rs.getInt("Dientich"),
                        rs.getString("Trangthai"),
                        maloaiphong,
                        makhach,
                        mats
                    );
                    list.add(p);
                }
            }
        }
        return list;
    }
public ArrayList<Integer> getDanhSachMaLoaiPhong() throws SQLException {
    ArrayList<Integer> list = new ArrayList<>();

    String sql = "SELECT Maloaiphong FROM loaiphong";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getInt("Maloaiphong"));
        }
    }

    return list;
}

    public ArrayList<Integer> getDanhSachMaKhach() throws SQLException {
    ArrayList<Integer> list = new ArrayList<>();
    String sql = "SELECT Makhach FROM khachthue";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getInt("Makhach"));
        }
    }
    return list;
}
public ArrayList<Integer> getDanhSachMaTaiSan() throws SQLException {
    ArrayList<Integer> list = new ArrayList<>();
    String sql = "SELECT Mats FROM taisan";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getInt("Mats"));
        }
    }
    return list;
}

}