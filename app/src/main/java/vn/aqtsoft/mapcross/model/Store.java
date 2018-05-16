package vn.aqtsoft.mapcross.model;

import android.graphics.Bitmap;

public class Store {
    private String Name;
    private String SDT;
    private String DiaChi;
    private String NoiDung;
    private Bitmap Logo;
    private String email;

    public Store(String name, String SDT, String diaChi, String noiDung, Bitmap logo, String email) {
        Name = name;
        this.SDT = SDT;
        DiaChi = diaChi;
        NoiDung = noiDung;
        Logo = logo;
        this.email = email;
    }

    public Store() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public Bitmap getLogo() {
        return Logo;
    }

    public void setLogo(Bitmap logo) {
        Logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
