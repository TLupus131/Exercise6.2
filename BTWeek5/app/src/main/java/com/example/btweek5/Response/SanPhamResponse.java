package com.example.btweek5.Response;

import com.example.btweek5.entities.SanPham;

public class SanPhamResponse {
    private int ma;
    private String ten;
    private double donGia;
    private int danhMuc;
    private String message;

    public int getMa() {
        return ma;
    }

    public SanPhamResponse(int ma, String ten, double donGia, int danhMuc) {
        this.ma = ma;
        this.ten = ten;
        this.donGia = donGia;
        this.danhMuc = danhMuc;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(int danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
