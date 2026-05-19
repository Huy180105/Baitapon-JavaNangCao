package model;

public class TaiSan {
    private int mats;              // AUTO_INCREMENT
    private String tents;
    private int soluong;
    private String tinhtrang;
    private String maphong;
    
    public TaiSan() {}
    
    public TaiSan(int mats, String tents, int soluong, String tinhtrang, String maphong) {
        this.mats = mats;
        this.tents = tents;
        this.soluong = soluong;
        this.tinhtrang = tinhtrang;
        this.maphong = maphong;
    }
    
    // Constructor không có ID (cho INSERT)
    public TaiSan(String tents, int soluong, String tinhtrang, String maphong) {
        this.tents = tents;
        this.soluong = soluong;
        this.tinhtrang = tinhtrang;
        this.maphong = maphong;
    }
    
    // Getters and Setters
    public int getMats() { return mats; }
    public void setMats(int mats) { this.mats = mats; }
    
    public String getTents() { return tents; }
    public void setTents(String tents) { this.tents = tents; }
    
    public int getSoluong() { return soluong; }
    public void setSoluong(int soluong) { this.soluong = soluong; }
    
    public String getTinhtrang() { return tinhtrang; }
    public void setTinhtrang(String tinhtrang) { this.tinhtrang = tinhtrang; }
    
    public String getMaphong() { return maphong; }
    public void setMaphong(String maphong) { this.maphong = maphong; }
}
