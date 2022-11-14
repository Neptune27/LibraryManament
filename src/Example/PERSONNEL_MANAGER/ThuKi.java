package Example.PERSONNEL_MANAGER;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ThuKi extends NhanVien implements Serializable {
    private int soSachMuon;
    private int soSoSachTra;
    private int sLNguoiMuon;
    private int sLNguoiTra;


    public ThuKi() {
    }

    public ThuKi(String maNV, String chucVu, String hoTen, String gioiTinh, int tuoi, String xepLoai, DiaChi diaChi,
                 String soDt, int soNgayNghi, String caLam, int soSachMuon, int soSoSachTra, int sLNguoiMuon, int sLNguoiTra) {
        super(maNV, chucVu, hoTen, gioiTinh, tuoi, xepLoai, diaChi,
                soDt, soNgayNghi, caLam);
        this.soSachMuon = soSachMuon;
        this.soSoSachTra = soSoSachTra;
        this.sLNguoiMuon = sLNguoiMuon;
        this.sLNguoiTra = sLNguoiTra;
    }

    public int getSoSachMuon() {
        return soSachMuon;
    }

    public int getSoSoSachTra() {
        return soSoSachTra;
    }

    public int getsLNguoiMuon() {
        return sLNguoiMuon;
    }

    public int getsLNguoiTra() {
        return sLNguoiTra;
    }

    public void setSoSachMuon(int soSachMuon) {
        this.soSachMuon = soSachMuon;
    }

    public void setSoSoSachTra(int soSoSachTra) {
        this.soSoSachTra = soSoSachTra;
    }

    public void setsLNguoiMuon(int sLNguoiMuon) {
        this.sLNguoiMuon = sLNguoiMuon;
    }

    public void setsLNguoiTra(int sLNguoiTra) {
        this.sLNguoiTra = sLNguoiTra;
    }

    @Override
    public boolean nhap(ArrayList<NhanVien> listNV) {
        if (listNV.isEmpty())
            return false;
        Scanner sc = new Scanner(System.in);
        System.out.printf("Nhap so sach da cho muon :");
        soSachMuon = sc.nextInt();
        System.out.printf("Nhap so sach khach hang da tra : ");
        soSoSachTra = sc.nextInt();
        System.out.printf("Nhap so luong nguoi muon :");
        sLNguoiMuon = sc.nextInt();
        System.out.printf("Nhap so luong nguoi tra :");
        sLNguoiTra = sc.nextInt();
        return true;
    }

    @Override
    public void xuat() {
        super.xuat();
    }

    @Override
    public float luong() {
        return (30 - getSoNgayNghi()) * 30000;
    }

    @Override
    public float thuong() {
        if (getXepLoai() == "A")
            return luong() * 15 / 100;
        else if (getXepLoai() == "B")
            return luong() * 10 / 100;
        else if (getXepLoai() == "C")
            return luong() * 5 / 100;
        return 0;
    }

    @Override
    public float tongLuong() {
        return luong() + thuong();
    }

    @Override
    public void xuatLuong() {
        super.xuatLuong();
    }

    public void menu(ArrayList<NhanVien> listNV,ArrayList<Account> listTKNV,ArrayList<Account> listTKKH,String tenDangNhap) {
        int luachon;
        do {
            System.out.println("\n------------Nhan Vien Thu Ki----------------");
            System.out.println("\tChức năng\t");
            System.out.println("1.In ra thong tin cua minh");
            System.out.println("2.Xem luong cua minh");
            System.out.println("3.Thong ke so luong sach va nguoi muon/tra");
            System.out.println("4.Nhap sach");
            System.out.println("5.Tao tai khoan cho KH");
            System.out.println("6.Dang ki muon sach");
            System.out.println("7.In hoa don muon sach");
            System.out.println("0.Thoát");
            System.out.println("----------------------------");
            luachon = scInt.nextInt();
            switch (luachon) {
                case 1:
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("|                                                           THONG TIN NHAN VIEN                                                         |");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.format("|%-10s|%-10s|%-18s|%-5s|%-10s|%-20s|%-15s|%-10s|%-15s|%-13s|\n",
                            "Chuc vu","Ma NV","Ho Ten","Tuoi","Gioi tinh","Dia chi","SDT","Xep loai","Ca lam","So ngay nghi");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
                    for (NhanVien i : listNV)
                        if (tenDangNhap.equalsIgnoreCase(i.getMaNV())) {
                            i.xuat();
                            break;
                        }
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("|                            LUONG NHAN VIEN                              |");
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.format("|%-10s|%-10s|%-18s|%-10s|%-10s|%-10s|\n",
                            "Chuc vu","Ma NV","Ho Ten","Luong","Thuong","Tong Luong");
                    System.out.println("---------------------------------------------------------------------------");
                    for (NhanVien i : listNV)
                        if (tenDangNhap.equalsIgnoreCase(i.getMaNV())) {
                            i.xuatLuong();
                            break;
                        }
                    System.out.println("---------------------------------------------------------------------------");

                    break;
                case 3:
                    System.out.format("|%-5d|%-5d|%-5d|%-5d|", soSachMuon, soSoSachTra, sLNguoiMuon, sLNguoiTra);
                    break;
                case 4:
                    //sach sach
                    break;
                case 5:
                    User  kh = new User();
                    Account tk = new Account();
                    kh.nhap(listNV);
                    tk.taoTaiKhoan(listTKNV,listTKKH,kh.getMaUser(),"Khach Hang");
                    listTKKH.add(tk);
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }while (luachon != 0);

    }
}


