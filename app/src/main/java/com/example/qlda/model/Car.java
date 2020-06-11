package com.example.qlda.model;

public class Car {
    private int id, namSX, anh, gia;
    private String tenXe, mauXe, thongSo;

    public Car(int id, int namSX, int anh, int gia, String tenXe, String mauXe, String thongSo) {
        this.id = id;
        this.namSX = namSX;
        this.anh = anh;
        this.gia = gia;
        this.tenXe = tenXe;
        this.mauXe = mauXe;
        this.thongSo = thongSo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getMauXe() {
        return mauXe;
    }

    public void setMauXe(String mauXe) {
        this.mauXe = mauXe;
    }

    public String getThongSo() {
        return thongSo;
    }

    public void setThongSo(String thongSo) {
        this.thongSo = thongSo;
    }


}
