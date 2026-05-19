package model;

public class Khach {

    private String maKhach;
    private String tenKhach;
    private String soDienThoai;
    private String cccd;
    private String diaChi;

    // ===== Constructor rỗng =====
    public Khach() {
    }

    // ===== Constructor đầy đủ =====
    public Khach(String maKhach, String tenKhach, String soDienThoai, String cccd, String diaChi) {
        this.maKhach = maKhach;
        this.tenKhach = tenKhach;
        this.soDienThoai = soDienThoai;
        this.cccd = cccd;
        this.diaChi = diaChi;
    }

    // ===== Getter & Setter =====
    public String getMaKhach() {
        return maKhach;
    }

    public void setMaKhach(String maKhach) {
        this.maKhach = maKhach;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
