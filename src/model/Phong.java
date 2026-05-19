package model;

public class Phong {
    private String maphong;
    private String tenphong;
    private int dientich;
    private String trangthai;
    private Integer maloaiphong;   // Có thể NULL
    private Integer makhach;       // Có thể NULL
    private Integer mats;          // Có thể NULL
    
    public Phong(String maphong1, String tenphong1, int dientich1, String trangthai1, Integer maloaiphong1, Integer makhach1) {}
    
    public Phong(String maphong, String tenphong, int dientich, String trangthai, 
                 Integer maloaiphong, Integer makhach, Integer mats) {
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.dientich = dientich;
        this.trangthai = trangthai;
        this.maloaiphong = maloaiphong;
        this.makhach = makhach;
        this.mats = mats;
    }
    
    // Getters and Setters
    public String getMaphong() { return maphong; }
    public void setMaphong(String maphong) { this.maphong = maphong; }
    
    public String getTenphong() { return tenphong; }
    public void setTenphong(String tenphong) { this.tenphong = tenphong; }
    
    public int getDientich() { return dientich; }
    public void setDientich(int dientich) { this.dientich = dientich; }
    
    public String getTrangthai() { return trangthai; }
    public void setTrangthai(String trangthai) { this.trangthai = trangthai; }
    
    public Integer getMaloaiphong() { return maloaiphong; }
    public void setMaloaiphong(Integer maloaiphong) { this.maloaiphong = maloaiphong; }
    
    public Integer getMakhach() { return makhach; }
    public void setMakhach(Integer makhach) { this.makhach = makhach; }
    
    public Integer getMats() { return mats; }
    public void setMats(Integer mats) { this.mats = mats; }
}
