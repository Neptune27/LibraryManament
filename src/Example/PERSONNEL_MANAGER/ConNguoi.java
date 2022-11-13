package Example.PERSONNEL_MANAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ConNguoi implements Serializable {
    private String hoTen;
    private String gioiTinh;
    private int tuoi;
    private DiaChi diaChi = new DiaChi();
    private String soDt;
    Scanner scText = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);
    public ConNguoi(){

    }
    public ConNguoi(String hoTen, String gioiTinh,int tuoi, DiaChi diaChi, String soDt){
        this.hoTen=hoTen;
        this.gioiTinh=gioiTinh;
        this.tuoi=tuoi;
        this.diaChi=diaChi;
        this.soDt=soDt;
    }
    public String getHoTen() {
        return hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getSoDt() {
        return soDt;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDt(String soDt) {
        this.soDt = soDt;
    }
    static void bangGioiTinh() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("|                          CHON GIOI TINH                          |");
        System.out.println("--------------------------------------------------------------------");
        System.out.format("|%-22s%-22s%-22s|\n", "Nam", "Nu", "Khac");
        System.out.println("--------------------------------------------------------------------");
    }
    public boolean nhap(ArrayList<NhanVien> listNV) {
        if (listNV.isEmpty()) {
            nhapListNULL(listNV);
            return true;
        }
        System.out.printf("Nhap ho va ten nhan vien: ");
        hoTen = scText.nextLine();
        while (true) {
            System.out.printf("Nhap tuoi nhan vien: ");
            tuoi = scInt.nextInt();
            if (tuoi >= 0)
                break;
            else
                System.out.println("Nhap sai yeu cau nhap lai...");
        }
        boolean chonGT = true;
        while (chonGT) {
            bangGioiTinh();
            System.out.printf("Nhap gioi tinh nhan vien: ");
            gioiTinh = scText.nextLine();
            switch (gioiTinh) {
                case "Nam":
                    chonGT = false;
                    break;
                case "Nu":
                    chonGT = false;
                    break;
                case "Khac":
                    chonGT = false;
                    break;
                default:
                    System.out.println("Nhap sai yeu cau nhap lai...");
                    break;
            }
        }
        while (true) {
            System.out.printf("Nhap so dien thoai nhan vien (gom 10 so): ");
            soDt = scText.nextLine();
            if (soDt.length() == 10)
                break;
            else
                System.out.printf("Nhap sai yeu cau nhap lai...");
        }
        System.out.printf("Nhap dia chi nhan vien: \n");
        diaChi.nhap();
        return true;
    }
    public boolean nhapListNULL(ArrayList<NhanVien> listNV) {
        System.out.printf("Nhap ho va ten nhan vien: ");
        hoTen = scText.nextLine();
        while (true) {
            System.out.printf("Nhap tuoi nhan vien: ");
            tuoi = scInt.nextInt();
            if (tuoi >= 0)
                break;
            else
                System.out.println("Nhap sai yeu cau nhap lai...");
        }
        boolean chonGT = true;
        while (chonGT) {
            bangGioiTinh();
            System.out.printf("Nhap gioi tinh nhan vien: ");
            gioiTinh = scText.nextLine();
            switch (gioiTinh) {
                case "Nam":
                    chonGT = false;
                    break;
                case "Nu":
                    chonGT = false;
                    break;
                case "Khac":
                    chonGT = false;
                    break;
                default:
                    System.out.println("Nhap sai yeu cau nhap lai...");
                    break;
            }
        }
        while (true) {
            System.out.printf("Nhap so dien thoai nhan vien (gom 10 so): ");
            soDt = scText.nextLine();
            if (soDt.length() == 10)
                break;
            else
                System.out.printf("Nhap sai yeu cau nhap lai...");
        }
        System.out.println("Nhap dia chi nhan vien: ");
        diaChi.nhap();
        return true;
    }
    public void xuat(){
        System.out.format("|%-18s|%-5d|%-10s|%-20s|%-15s", hoTen,tuoi,gioiTinh,diaChi.toString(),soDt);
    }

    @Override
    public String toString() {
        return "ConNguoi{" +
                "hoTen='" + hoTen + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", tuoi=" + tuoi +
                ", diaChi=" + diaChi.toString() +
                ", soDt='" + soDt + '\'' +
                ", scText=" + scText +
                '}';
    }
}
