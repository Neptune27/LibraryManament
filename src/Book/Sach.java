package Book;

import java.io.Serializable;

public abstract class Sach implements Serializable,Cloneable {
    private String maSach;
    private String tenSach;
    private String tenTacGia;
    private int nxb;
    private int soLuongMoiCuon;
    private static int soLuong;
    private int soLuong1;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String tenTacGia, int nxb, int soLuongMoiCuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.nxb = nxb;
        this.soLuongMoiCuon = soLuongMoiCuon;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }



    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getNxb() {
        return nxb;
    }

    public void setNxb(int nxb) {
        this.nxb = nxb;
    }

    public int getSoLuongMoiCuon() {
        return soLuongMoiCuon;
    }

    public void setSoLuongMoiCuon(int soLuongMoiCuon) {
        this.soLuongMoiCuon = soLuongMoiCuon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        Sach.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return  "maSach='" + maSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", tenTacGia='" + tenTacGia + '\'' +
                ", nxb=" + nxb +
                ", soLuong=" + soLuongMoiCuon;
    }



    public int getSoLuong1() {
        return soLuong1;
    }

    public void setSoLuong1(int soLuong1) {
        this.soLuong1 = soLuong1;
    }
}
