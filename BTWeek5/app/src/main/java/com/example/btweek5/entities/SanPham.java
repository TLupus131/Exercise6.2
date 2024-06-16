package com.example.btweek5.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SanPham implements Parcelable {
    private int ma;
    private String ten;
    private double donGia;
    private String tenDanhMuc;

    public SanPham() {
    }

    public SanPham(int ma, String ten, double donGia, String tenDanhMuc) {
        this.ma = ma;
        this.ten = ten;
        this.donGia = donGia;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getMa() {
        return ma;
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

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(ma);
        dest.writeString(ten);
        dest.writeDouble(donGia);
        dest.writeString(tenDanhMuc);
    }

    protected SanPham(Parcel in) {
        ma = in.readInt();
        ten = in.readString();
        donGia = in.readDouble();
        tenDanhMuc = in.readString();
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };
}
