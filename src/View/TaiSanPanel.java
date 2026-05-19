package View;



import controller.TaiSanController;
import database.DBConnection;
import model.TaiSan;
import view.TaiSanView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TaiSanPanel extends JPanel implements TaiSanView {
    private TaiSanController controller;
    private Connection connection;
    
    // Components
    private JTextField txtTenTaiSan, txtSoLuong, txtMaPhong, txtTimKiem;
    private JComboBox<String> cboTinhTrang;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private int selectedMats = -1;
    
    public TaiSanPanel() {
        initComponents();
        initController();
        loadData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Tài sản"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Row 0
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Tên tài sản:"), gbc);
        gbc.gridx = 1;
        txtTenTaiSan = new JTextField(20);
        inputPanel.add(txtTenTaiSan, gbc);
        
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 3;
        txtSoLuong = new JTextField(20);
        inputPanel.add(txtSoLuong, gbc);
        
        // Row 1
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Tình trạng:"), gbc);
        gbc.gridx = 1;
        cboTinhTrang = new JComboBox<>(new String[]{"Tốt", "Cần sửa chữa", "Hỏng"});
        inputPanel.add(cboTinhTrang, gbc);
        
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Mã phòng:"), gbc);
        gbc.gridx = 3;
        txtMaPhong = new JTextField(20);
        inputPanel.add(txtMaPhong, gbc);
        
        add(inputPanel, BorderLayout.NORTH);
        
        // Panel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");
        
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        
        // Panel tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Tìm kiếm:"));
        txtTimKiem = new JTextField(30);
        searchPanel.add(txtTimKiem);
        btnTimKiem = new JButton("Tìm kiếm");
        searchPanel.add(btnTimKiem);
        
        // Table
        String[] columns = {"Mã TS", "Tên tài sản", "Số lượng", "Tình trạng", "Mã phòng"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Event handlers
        btnThem.addActionListener(e -> themTaiSan());
        btnSua.addActionListener(e -> suaTaiSan());
        btnXoa.addActionListener(e -> xoaTaiSan());
        btnLamMoi.addActionListener(e -> controller.lamMoi());
        btnTimKiem.addActionListener(e -> controller.timKiemTaiSan(txtTimKiem.getText()));
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedRow();
            }
        });
    }
    
    private void initController() {
        try {
            connection = DBConnection.getConnection();
            controller = new TaiSanController(connection, this);
        } catch (SQLException e) {
            hienThiLoi("Không thể kết nối database: " + e.getMessage());
        }
    }
    
    private void loadData() {
        controller.loadDanhSachTaiSan();
    }
    
    private void themTaiSan() {
        if (validateInput()) {
            TaiSan taiSan = getTaiSanFromInput();
            controller.themTaiSan(taiSan);
        }
    }
    
    private void suaTaiSan() {
        if (selectedMats == -1) {
            hienThiLoi("Vui lòng chọn tài sản cần sửa!");
            return;
        }
        if (validateInput()) {
            TaiSan taiSan = getTaiSanFromInput();
            taiSan.setMats(selectedMats);
            controller.suaTaiSan(taiSan);
        }
    }
    
    private void xoaTaiSan() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            hienThiLoi("Vui lòng chọn tài sản cần xóa!");
            return;
        }
        
        int mats = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc muốn xóa tài sản này?",
            "Xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            controller.xoaTaiSan(mats);
        }
    }
    
    private boolean validateInput() {
        if (txtTenTaiSan.getText().trim().isEmpty()) {
            hienThiLoi("Vui lòng nhập tên tài sản!");
            txtTenTaiSan.requestFocus();
            return false;
        }
        try {
            int sl = Integer.parseInt(txtSoLuong.getText().trim());
            if (sl < 0) {
                hienThiLoi("Số lượng phải >= 0!");
                return false;
            }
        } catch (NumberFormatException e) {
            hienThiLoi("Số lượng phải là số!");
            txtSoLuong.requestFocus();
            return false;
        }
        return true;
    }
    
    private TaiSan getTaiSanFromInput() {
        String tents = txtTenTaiSan.getText().trim();
        int soluong = Integer.parseInt(txtSoLuong.getText().trim());
        String tinhtrang = cboTinhTrang.getSelectedItem().toString();
        String maphong = txtMaPhong.getText().trim();
        
        if (maphong.isEmpty()) {
            maphong = null;
        }
        
        return new TaiSan(tents, soluong, tinhtrang, maphong);
    }
    
    private void displaySelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            selectedMats = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            txtTenTaiSan.setText(table.getValueAt(selectedRow, 1).toString());
            txtSoLuong.setText(table.getValueAt(selectedRow, 2).toString());
            cboTinhTrang.setSelectedItem(table.getValueAt(selectedRow, 3).toString());
            
            Object maphong = table.getValueAt(selectedRow, 4);
            txtMaPhong.setText(maphong != null ? maphong.toString() : "");
        }
    }
    
    @Override
    public void hienThiDanhSach(List<TaiSan> list) {
        tableModel.setRowCount(0);
        for (TaiSan ts : list) {
            Object[] row = {
                ts.getMats(),
                ts.getTents(),
                ts.getSoluong(),
                ts.getTinhtrang(),
                ts.getMaphong()
            };
            tableModel.addRow(row);
        }
    }
    
    @Override
    public void hienThiThongBao(String message) {
        JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void hienThiLoi(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void xoaTrangForm() {
        txtTenTaiSan.setText("");
        txtSoLuong.setText("");
        cboTinhTrang.setSelectedIndex(0);
        txtMaPhong.setText("");
        txtTimKiem.setText("");
        table.clearSelection();
        selectedMats = -1;
    }
}