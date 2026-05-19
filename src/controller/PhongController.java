package controller;

import dao.PhongDAO;
import model.Phong;
import view.PhongView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PhongController {
    private PhongDAO phongDAO;
    private PhongView view;
    
    public PhongController(Connection connection, PhongView view) {
        this.phongDAO = new PhongDAO(connection);
        this.view = view;
    }
    
    public void loadDanhSachPhong() {
        try {
            List<Phong> list = phongDAO.getAllPhong();
            view.hienThiDanhSach(list);
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi tải danh sách phòng: " + e.getMessage());
        }
    }
    
    public void themPhong(Phong phong) {
        try {
            if (phongDAO.themPhong(phong)) {
                view.hienThiThongBao("Thêm phòng thành công!");
                loadDanhSachPhong();
            } else {
                view.hienThiLoi("Không thể thêm phòng!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi thêm phòng: " + e.getMessage());
        }
    }
    
    public void suaPhong(Phong phong) {
        try {
            if (phongDAO.suaPhong(phong)) {
                view.hienThiThongBao("Sửa phòng thành công!");
                loadDanhSachPhong();
            } else {
                view.hienThiLoi("Không thể sửa phòng!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi sửa phòng: " + e.getMessage());
        }
    }
    
    public void xoaPhong(String maphong) {
        try {
            if (phongDAO.xoaPhong(maphong)) {
                view.hienThiThongBao("Xóa phòng thành công!");
                loadDanhSachPhong();
            } else {
                view.hienThiLoi("Không thể xóa phòng!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi xóa phòng: " + e.getMessage());
        }
    }
    
    public void timKiemPhong(String keyword) {
        try {
            List<Phong> list = phongDAO.timKiemPhong(keyword);
            view.hienThiDanhSach(list);
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }
    
    public void lamMoi() {
        view.xoaTrangForm();
        loadDanhSachPhong();
    }
}