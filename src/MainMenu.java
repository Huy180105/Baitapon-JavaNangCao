// Đảm bảo tên package khớp với thư mục của bạn

import View.PhongForm;
import javax.swing.*;
import java.awt.*;
import View.TaiSanPanel; // Import Panel tài sản
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
// import view.LoaiPhongForm; // Bỏ comment nếu khác package
// import view.PhongForm;     // Bỏ comment nếu khác package

public class MainMenu extends JFrame {

    public MainMenu() {
        initComponents();
        setTitle("HỆ THỐNG QUẢN LÝ KÝ TÚC XÁ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null); // Hiển thị giữa màn hình
    }

    private void initComponents() {
        // Layout chính
        setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel lblTitle = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Panel chứa các nút bấm
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50));

        JButton btnLoaiPhong = new JButton("1. Quản lý Loại Phòng");
        JButton btnPhong = new JButton("2. Quản lý Phòng");
        JButton btnTaiSan = new JButton("3. Quản lý Tài Sản");

        // Font cho nút
        Font btnFont = new Font("Arial", Font.PLAIN, 16);
        btnLoaiPhong.setFont(btnFont);
        btnPhong.setFont(btnFont);
        btnTaiSan.setFont(btnFont);

        // Thêm sự kiện Click
        btnLoaiPhong.addActionListener(e -> {
            try {
                new LoaiPhongForm().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnPhong.addActionListener(e -> {
            try {
                new PhongForm().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnTaiSan.addActionListener(e -> {
            // Vì TaiSanPanel là JPanel nên cần bỏ vào JFrame để hiển thị độc lập
            JFrame tsFrame = new JFrame("Quản lý Tài Sản");
            tsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tsFrame.add(new TaiSanPanel());
            tsFrame.pack(); // Tự động co giãn theo Panel
            tsFrame.setLocationRelativeTo(this);
            tsFrame.setVisible(true);
        });

        buttonPanel.add(btnLoaiPhong);
        buttonPanel.add(btnPhong);
        buttonPanel.add(btnTaiSan);

        add(buttonPanel, BorderLayout.CENTER);
    }
}