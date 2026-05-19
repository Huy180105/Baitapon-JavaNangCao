package controller;

import dao.LoaiPhongDAO;
import model.LoaiPhong;
import view.LoaiPhongView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LoaiPhongController {
    private LoaiPhongDAO loaiPhongDAO;
    private LoaiPhongView view;
    
    public LoaiPhongController(Connection connection, LoaiPhongView view) {
        this.loaiPhongDAO = new LoaiPhongDAO(connection);
        this.view = view;
    }
    
    public void loadDanhSachLoaiPhong() {
        try {
            List<LoaiPhong> list = loaiPhongDAO.getAllLoaiPhong();
            view.hienThiDanhSach(list);
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi tải danh sách loại phòng: " + e.getMessage());
        }
    }
    
    public void themLoaiPhong(LoaiPhong loaiPhong) {
        try {
            if (loaiPhongDAO.themLoaiPhong(loaiPhong)) {
                view.hienThiThongBao("Thêm loại phòng thành công!");
                loadDanhSachLoaiPhong();
            } else {
                view.hienThiLoi("Không thể thêm loại phòng!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi thêm loại phòng: " + e.getMessage());
        }
    }
    
    public void suaLoaiPhong(LoaiPhong loaiPhong) {
        try {
            if (loaiPhongDAO.suaLoaiPhong(loaiPhong)) {
                view.hienThiThongBao("Sửa loại phòng thành công!");
                loadDanhSachLoaiPhong();
            } else {
                view.hienThiLoi("Không thể sửa loại phòng!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi sửa loại phòng: " + e.getMessage());
        }
    }
    
    public void xoaLoaiPhong(int maloaiphong) {
        try {
            if (loaiPhongDAO.xoaLoaiPhong(maloaiphong)) {
                view.hienThiThongBao("Xóa loại phòng thành công!");
                loadDanhSachLoaiPhong();
            } else {
                view.hienThiLoi("Không thể xóa loại phòng!");
            }
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi xóa loại phòng: " + e.getMessage());
        }
    }
    
    public void timKiemLoaiPhong(String keyword) {
        try {
            List<LoaiPhong> list = loaiPhongDAO.timKiemLoaiPhong(keyword);
            view.hienThiDanhSach(list);
        } catch (SQLException e) {
            view.hienThiLoi("Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }
    
    public void lamMoi() {
        view.xoaTrangForm();
        loadDanhSachLoaiPhong();
    }
}


