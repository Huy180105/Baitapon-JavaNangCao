package controller;

import dao.TaiSanDAO;
import model.TaiSan;
import view.TaiSanView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TaiSanController {
    private TaiSanDAO taiSanDAO;
    private TaiSanView view;
    
    public TaiSanController(Connection connection, TaiSanView view) {
        this.taiSanDAO = new TaiSanDAO(connection);
        this.view = view;
    }
    
    public void loadDanhSachTaiSan() {
        try {
            List<TaiSan> list = taiSanDAO.getAllTaiSan();
            view.hienThiDanhSach(list);
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi tải danh sách tài sản: " + e.getMessage());
        }
    }
    
    public void themTaiSan(TaiSan taiSan) {
        try {
            if (taiSanDAO.themTaiSan(taiSan)) {
                view.hienThiThongBao("Thêm tài sản thành công!");
                loadDanhSachTaiSan();
            } else {
                view.hienThiLoi("Không thể thêm tài sản!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi thêm tài sản: " + e.getMessage());
        }
    }
    
    public void suaTaiSan(TaiSan taiSan) {
        try {
            if (taiSanDAO.suaTaiSan(taiSan)) {
                view.hienThiThongBao("Sửa tài sản thành công!");
                loadDanhSachTaiSan();
            } else {
                view.hienThiLoi("Không thể sửa tài sản!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi sửa tài sản: " + e.getMessage());
        }
    }
    
    public void xoaTaiSan(int mats) {
        try {
            if (taiSanDAO.xoaTaiSan(mats)) {
                view.hienThiThongBao("Xóa tài sản thành công!");
                loadDanhSachTaiSan();
            } else {
                view.hienThiLoi("Không thể xóa tài sản!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi xóa tài sản: " + e.getMessage());
        }
    }
    
    public void timKiemTaiSan(String keyword) {
        try {
            List<TaiSan> list = taiSanDAO.timKiemTaiSan(keyword);
            view.hienThiDanhSach(list);
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }
    
    public void lamMoi() {
        view.xoaTrangForm();
        loadDanhSachTaiSan();
    }
}