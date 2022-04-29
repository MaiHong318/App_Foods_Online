package com.example.epapp_demo.model.local.modul;

import java.util.HashMap;
import java.util.Map;

public class CartDetails {
    private int giohangId;
    private String MonAnId;
    private String tenMonAn;
    private int gia;
    private int soluong;
    private String hinh;

    public CartDetails() {
    }

    public CartDetails(int giohangId, String monAnId, String tenMonAn, int gia, int soluong, String hinh) {
        this.giohangId = giohangId;
        MonAnId = monAnId;
        this.tenMonAn = tenMonAn;
        this.gia = gia;
        this.soluong = soluong;
        this.hinh = hinh;
    }

    public int getGiohangId() {
        return giohangId;
    }

    public String getMonAnId() {
        return MonAnId;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public int getGia() {
        return gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public String getHinh() {
        return hinh;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
//        result.put("giohangId",giohangId);
//        result.put("MonAnId",MonAnId);
        result.put("tenMonAn",tenMonAn);
        result.put("gia",gia);
        result.put("soluong",soluong);
        result.put("hinh",hinh);
        return  result;
    }
}
