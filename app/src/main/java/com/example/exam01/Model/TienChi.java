package com.example.exam01.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TienChi {
    private static int  TongTien = 3000000;
    private String loaiKhoanChi;
    private Date ngayChi;
    private String tenKhoanChi;
    private int soTienChi;


    public TienChi(String loaiKhoanChi, Date ngayChi, String tenKhoanChi, int soTienChi) {
        this.loaiKhoanChi = loaiKhoanChi;
        this.ngayChi = ngayChi;
        this.tenKhoanChi = tenKhoanChi;
        this.soTienChi = soTienChi;
    }

    public static int getTongTien() {
        return TongTien;
    }

    public static void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public String getLoaiKhoanChi() {
        return loaiKhoanChi;
    }

    public void setLoaiKhoanChi(String loaiKhoanChi) {
        this.loaiKhoanChi = loaiKhoanChi;
    }

    public String getNgayChi() {
        return DateFormatNgay(this.ngayChi);
    }

    public void setNgayChi(Date ngayChi) {
        this.ngayChi = ngayChi;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public void setTenKhoanChi(String tenKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
    }

    public int getSoTienChi() {
        return soTienChi;
    }

    public void setSoTienChi(int soTienChi) {
        this.soTienChi = soTienChi;
    }
    public String DateFormatNgay(Date d){
        DateFormat dd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dd.format(d);
    }

}
