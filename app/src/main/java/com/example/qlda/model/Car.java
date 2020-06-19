package com.example.qlda.model;

import androidx.annotation.Nullable;

public class Car {
    private int id, namSX, anh[], gia, idFavourite;
    private String tenXe, thongSo;

    public Car(int id, int namSX, int anh, int gia, String tenXe, String thongSo, int idFavourite) {
        this.id = id;
        this.namSX = namSX;
        this.gia = gia;
        this.tenXe = tenXe;
        this.thongSo = thongSo;
        this.anh = new int[3];
        this.idFavourite = idFavourite;
        this.anh[0] = anh;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Car) {
            return id == ((Car) obj).getId();
        }
        return super.equals(obj);
    }

    public int getIdFavourite() {
        return idFavourite;
    }

    public void setIdFavourite(int idFavourite) {
        this.idFavourite = idFavourite;
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

    public int[] getAnh() {
        return anh;
    }

    public void setAnh(int[] anh) {
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


    public String getThongSo() {
        return thongSo;
    }

    public void setThongSo(String thongSo) {
        this.thongSo = thongSo;
    }


}
