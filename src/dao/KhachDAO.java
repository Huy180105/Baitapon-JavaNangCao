package dao;

import database.DBConnection;
import java.sql.*;
import java.util.*;

public class KhachDAO {

    public List<Integer> getAllMaKhach() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT Makhach FROM khachthue";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("Makhach"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
