package model;
public class LoaiPhong {
    private int maloaiphong;      // AUTO_INCREMENT
    private String tenloai;
    private double dongia;
    
    public LoaiPhong() {}
    
    public LoaiPhong(int maloaiphong, String tenloai, double dongia) {
        this.maloaiphong = maloaiphong;
        this.tenloai = tenloai;
        this.dongia = dongia;
    }
    
    // Constructor không có ID (cho INSERT)
    public LoaiPhong(String tenloai, double dongia) {
        this.tenloai = tenloai;
        this.dongia = dongia;
    }
    
    // Getters and Setters
    public int getMaloaiphong() { return maloaiphong; }
    public void setMaloaiphong(int maloaiphong) { this.maloaiphong = maloaiphong; }
    
    public String getTenloai() { return tenloai; }
    public void setTenloai(String tenloai) { this.tenloai = tenloai; }
    
    public double getDongia() { return dongia; }
    public void setDongia(double dongia) { this.dongia = dongia; }
}