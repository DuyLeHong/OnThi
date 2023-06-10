package com.duyle.onthi;

import java.io.Serializable;

public class SinhVienModel implements Serializable {

    private String ten;
    private String mssv;
    private int diemTB;

    public SinhVienModel(String ten, String mssv, int diemTB) {
        this.ten = ten;
        this.mssv = mssv;
        this.diemTB = diemTB;
    }


    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public int getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(int diemTB) {
        this.diemTB = diemTB;
    }
}
