package com.example.epapp_demo.model.local.modul;

import java.io.Serializable;

public class Cart implements Serializable {
    private int gioHangId;
    private String DonHangID;
    private String MonAnID;
//    private String NameMonAn;
    private int SoLuong;
//    private int Gia;
//    private String HinhAnhGioHang;
    private String CuaHangID;

    public int getGioHangId() {
        return gioHangId;
    }

    public void setGioHangId(int gioHangId) {
        this.gioHangId = gioHangId;
    }

    public String getStoreID() {
        return DonHangID;
    }

    public void setStoreID(String storeID) {
        DonHangID = storeID;
    }

    public String getMonAnID() {
        return MonAnID;
    }

    public void setMonAnID(String monAnID) {
        MonAnID = monAnID;
    }

//    public String getNameMonAn() {
//        return NameMonAn;
//    }
//
//    public void setNameMonAn(String nameMonAn) {
//        NameMonAn = nameMonAn;
//    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

//    public int getGia() {
//        return Gia;
//    }
//
//    public void setGia(int gia) {
//        Gia = gia;
//    }
//
//    public String getHinhAnhGioHang() {
//        return HinhAnhGioHang;
//    }
//
//    public void setHinhAnhGioHang(String hinhAnhGioHang) {
//        HinhAnhGioHang = hinhAnhGioHang;
//    }


    public String getDonHangID() {
        return DonHangID;
    }

    public void setDonHangID(String donHangID) {
        DonHangID = donHangID;
    }

    public String getCuaHangID() {
        return CuaHangID;
    }

    public void setCuaHangID(String cuaHangID) {
        CuaHangID = cuaHangID;
    }

    public Cart() {
    }

    public Cart(String DonHangID, String monAnID, int soLuong) {
        this.DonHangID = DonHangID;
        MonAnID = monAnID;
        SoLuong = soLuong;
    }

    public Cart(int gioHangId, String DonHangID, String monAnID, int soLuong) {
        this.gioHangId = gioHangId;
        this.DonHangID = DonHangID;
        MonAnID = monAnID;
        SoLuong = soLuong;
    }

    public Cart(int gioHangId, String donHangID, String monAnID, int soLuong, String cuaHangID) {
        this.gioHangId = gioHangId;
        DonHangID = donHangID;
        MonAnID = monAnID;
        SoLuong = soLuong;
        CuaHangID = cuaHangID;
    }
}
