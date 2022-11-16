package Example.PERSONNEL_MANAGER;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLi extends NhanVien implements Serializable, LuuDocFile {
    private int tongNV;
    public static ArrayList<NhanVien> danhSachNV = new ArrayList<>();
    public static QuanLiTaiKhoan danhSachTK = new QuanLiTaiKhoan();
    public static QuanLiTaiKhoan danhSachTKKH = new QuanLiTaiKhoan();
    public static User user = new User();



    String[] mangCV = {"Phuc vu", "Thu ki", "Quan li"};
    String[] mangGT = {"Nam", "Nu", "Khac"};
    String[] mangBuoiLam = {"Lam buoi sang", "Lam buoi chieu", "Lam ca 2 buoi"};
    public QuanLi() {
    }

    public QuanLi(String maNV, String chucVu, String hoTen, String gioiTinh, int tuoi, String xepLoai,
                  DiaChi diaChi, String soDt, float soNgayNghi, String caLam, int tongNV, ArrayList<NhanVien> danhSachNV) {
        super(maNV, chucVu, hoTen, gioiTinh, tuoi, xepLoai,
                diaChi, soDt, soNgayNghi, caLam);
        this.tongNV = tongNV;
        this.danhSachNV = danhSachNV;
    }

    public void setDanhSachTK(QuanLiTaiKhoan danhSachTK) {
        QuanLi.danhSachTK = danhSachTK;
    }

    public void setDanhSachTKKH(QuanLiTaiKhoan danhSachTKKH) {
        QuanLi.danhSachTKKH = danhSachTKKH;
    }

    public void setDanhSachNV(ArrayList<NhanVien> danhSachNV) {
        this.danhSachNV = danhSachNV;
    }

    public QuanLiTaiKhoan getDanhSachTK() {
        return danhSachTK;
    }

    public QuanLiTaiKhoan getDanhSachTKKH() {
        return danhSachTKKH;
    }

    public ArrayList<NhanVien> getDanhSachNV() {
        return danhSachNV;
    }
    public int getTongNV() {
        return tongNV;
    }

    public void setTongNV(int tongNV) {
        this.tongNV = tongNV;
    }

    @Override
    public boolean nhap(ArrayList<NhanVien> listNV) {
        return super.nhap(listNV);
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.format("|%-5d|", tongNV);
    }

    @Override
    public float luong() {
        return (30 - getSoNgayNghi()) * 50000;
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
        System.out.format("|%-10fVND|%-10fVND|%-10fVND", luong(), thuong(), tongLuong());
    }

    @Override
    public void Save() {
        File wfile = new File(".\\src\\Example\\PERSONNEL_MANAGER\\FILE\\NhanVien.bin");
        try {
            FileOutputStream os = new FileOutputStream(wfile);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (NhanVien nhanVien : danhSachNV) {
                oos.writeObject(nhanVien);
            }
            oos.flush();
            oos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Read() {
        File rfile = new File(".\\src\\Example\\PERSONNEL_MANAGER\\FILE\\NhanVien.bin");
        try {
            FileInputStream is = new FileInputStream(rfile);
            ObjectInputStream ois = new ObjectInputStream(is);
            NhanVien nhanVien;
            while (true) {
                nhanVien = (NhanVien) ois.readObject();
                this.danhSachNV.add(nhanVien);
                if (nhanVien == null ) break;
            }
            ois.close();
            is.close();
        } catch (Exception e) {
            System.out.println("Thanh cong");
        }
    }

    public boolean TimKiemNV(String maNV) {
        for (int i=0; i<danhSachNV.size();i++) {
            if (maNV.equalsIgnoreCase(danhSachNV.get(i).getMaNV())) {
                return true;
            }
        }
        return false;

    }

    public boolean ThemNV(NhanVien nv) {
        if (!TimKiemNV(nv.getMaNV())) {
            danhSachNV.add(nv);
            return true;
        }
        return false;

    }

    public boolean XoaNV(String maNVien) {
        for (int i=0; i<danhSachNV.size();i++) {
            if (maNVien.equalsIgnoreCase(danhSachNV.get(i).getMaNV())) {
                danhSachNV.remove(i);
                return true;
            }
        }
        return false;
    }

    static void bangMucInf() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("|                       CHON YEU CAU CHINH SUA                     |");
        System.out.println("--------------------------------------------------------------------");
        System.out.format("|%-17s%-17s%-17s%-17s|\n", "1.Chuc vu", "2.Ho Ten", "3.Gioi Tinh", "4.Tuoi");
        System.out.format("|%-10s%-8s%-16s%-10s%-11s|\n", "5.Dia Chi", "6.So DT", "7.So Ngay Nghi", "8.Ca Lam", "0.Ket thuc");
        System.out.println("--------------------------------------------------------------------");
        ;
    }


//    TODO Refactor this too.
    public boolean suaThongTinNV(String maNV) {
        if (TimKiemNV(maNV)) {
            int chon[] = new int[9];
            while (true) {
                bangMucInf();
                System.out.printf("Nhap muc yeu cau chinh sửa: ");
                chon[0] = scInt.nextInt();
                switch (chon[0]) {
                    case 1:
                        bangChucVu();
                        System.out.printf("Nhap value: ");
                        chon[1] = scInt.nextInt();
                        for (int i = 0; i < danhSachNV.size(); i++) {
                            if (maNV.equalsIgnoreCase( danhSachNV.get(i).getMaNV())) {
                                danhSachNV.get(i).setChucVu(mangCV[chon[1] - 1]);
                                break;
                            }
                        }
                        break;
                    case 2:
                        System.out.printf("Nhap Ho Ten : ");
                        for (int i = 0; i < danhSachNV.size(); i++) {
                            if (maNV.equalsIgnoreCase( danhSachNV.get(i).getMaNV())) {
                                danhSachNV.get(i).setHoTen(scText.nextLine());
                                break;
                            }
                        }
                        break;
                    case 3:
                        bangGioiTinh();
                        System.out.printf("Nhap value: ");
                        chon[3] = scInt.nextInt();
                        for (int i = 0; i < danhSachNV.size(); i++) {
                            if (maNV.equalsIgnoreCase( danhSachNV.get(i).getMaNV())) {
                                danhSachNV.get(i).setGioiTinh(mangGT[chon[3] - 1]);
                                break;
                            }
                        }
                        break;
                    case 4:
                        System.out.printf("Nhap tuoi : ");
                        for (int i = 0; i < danhSachNV.size(); i++) {
                            if (maNV.equalsIgnoreCase( danhSachNV.get(i).getMaNV())) {
                                danhSachNV.get(i).setTuoi(scInt.nextInt());
                                break;
                            }
                        }
                        break;
                    case 5:
                        System.out.printf("Nhap dia chi: \n");
                        DiaChi dc = new DiaChi();
                        dc.nhap();
                        for (int i = 0; i < danhSachNV.size(); i++) {
                            if (maNV.equalsIgnoreCase( danhSachNV.get(i).getMaNV())) {
                                danhSachNV.get(i).setDiaChi(dc);
                                break;
                            }
                        }
                        break;
                    case 6:
                        for (int i = 0; i < danhSachNV.size(); i++) {
                            if (maNV.equalsIgnoreCase( danhSachNV.get(i).getMaNV())) {
                                System.out.printf("Nhap so dien thoai (gom 10 so)");
                                danhSachNV.get(i).setSoDt(scText.nextLine());
                                break;
                            }
                        }
                        break;
                    case 7:
                        for (int i = 0; i < danhSachNV.size(); i++) {
                            if (maNV.equalsIgnoreCase( danhSachNV.get(i).getMaNV())) {
                                System.out.printf("Nhap so ngay nghi can chinh: ");
                                danhSachNV.get(i).setSoNgayNghi(Float.parseFloat(scText.nextLine()));
                                break;
                            }
                        }
                    case 8:
                        bangCaLam();
                        System.out.printf("Nhap value: ");
                        chon[8] = scInt.nextInt();
                        for (int i = 0; i < danhSachNV.size(); i++) {
                            if (maNV.equalsIgnoreCase( danhSachNV.get(i).getMaNV())) {
                                danhSachNV.get(i).setCaLam(mangBuoiLam[chon[8] - 1]);
                                break;
                            }
                        }
                    default:
                        return true;


                }
            }
        } else {
            return false;
        }
    }


//    TODO Refactor me.
    public void menu() {
        int luachon;
        do {
            System.out.println("------------Nhan Vien Quan Li----------------");
            System.out.println("\tChức năng\t");
            System.out.println("1.Thong ke ra thong tin Nhan Vien");
            System.out.println("2.Thong ke luong cua Nhan Vien");
            System.out.println("3.Thong ke tai khoan cua nhan vien");
            System.out.println("3.So luong nhan vien cua Thu Vien");
            System.out.println("4.So luong khach hang muon sach");
            System.out.println("5.Tao tai khoan cho khach hang");
            System.out.println("6.Tim kiem nhan vien");
            System.out.println("7.Them nhan vien moi");
            System.out.println("8.Xoa nhan vien ");
            System.out.println("9.Dieu chinh thong tin cua nhan vien ");
            System.out.println("10.Dang ki muon sach");
            System.out.println("11.In hoa don muon sach");
            System.out.println("12.Thong ke tai khoan/mat khau");

            System.out.println("----------------------------");
            System.out.println("\tCập nhật CSDL\t");
            System.out.println("13.Đọc CSDL vào ArrayList");
            System.out.println("14.Ghi mới từ ArrayList vào CSDL");
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
                    for (NhanVien i : danhSachNV)
                        i.xuat();
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("|                            LUONG NHAN VIEN                              |");
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.format("|%-10s|%-10s|%-18s|%-10s|%-10s|%-10s|\n",
                            "Chuc vu","Ma NV","Ho Ten","Luong","Thuong","Tong Luong");
                    System.out.println("---------------------------------------------------------------------------");
                    for (NhanVien i : danhSachNV)
                        i.xuatLuong();
                    System.out.println("---------------------------------------------------------------------------");
                    break;
                case 3:
                    System.out.printf("Tong so nhan vien cua thu vien la: " + danhSachNV.size());
                    break;
                case 4:
                    //SL khach hang muon sach
                    break;
                case 5:
                    user.nhapUser(user.getDanhsachKH(),danhSachNV);
                    Account accKH = new Account();
                    accKH.taoTaiKhoan(danhSachTKKH.getListAccounts(),danhSachTK.getListAccounts(), user.getMaUser(), "Khanh hang");
                    break;
                case 6:
                    Scanner sc= new Scanner(System.in);
                    System.out.print("Nhap ma nhan vien can tim kiem: ");
                    String maNhV = sc.nextLine();
                    int tmp = -1;
                    for (NhanVien nv : danhSachNV) {
                        if (TimKiemNV(maNhV)) {
                            tmp = 1;
                            nv.xuat();
                        }
                    }
                    if (tmp == -1)
                        System.out.println("Khong co ten");
                    break;
                case 7:
                    NhanVien nv = new NhanVien();
                    Account tk = new Account();
                    if(!nv.nhap(danhSachNV)) {
                        nv.nhapListNULL(danhSachNV);
                    }
                    tk.taoTaiKhoan(danhSachTK.getListAccounts(),danhSachTKKH.getListAccounts(),nv.getMaNV(),nv.getChucVu());
                    if(ThemNV(nv))
                        danhSachTK.ThemTK(tk);
                    break;
                case 8://xoa
                    System.out.print("Nhap ma nhan vien can xoa: ");
                    String maNVxoa = scText.nextLine();
                    if(danhSachNV.isEmpty())
                        System.out.println("Danh sach hien tai dang rong");
                    else if (TimKiemNV(maNVxoa)) {
                        for (int i=0; i<danhSachNV.size();i++) {
                            if (maNVxoa.equalsIgnoreCase(danhSachNV.get(i).getMaNV())) {
                                danhSachNV.remove(danhSachNV.get(i));
                                danhSachTK.XoaTK(danhSachTK.getListAccounts().get(i));
                                break;
                            }
                        }

                    }
                    else
                        System.out.print("Khong co nhan vien nay ton tai...");
                    break;
                case 9:
                    System.out.print("Nhap ma nhan vien can chinh sua: ");
                    String maNVfix = scText.nextLine();
                    if (TimKiemNV(maNVfix))
                        suaThongTinNV(maNVfix);
                    else
                        System.out.print("Khong co nhan vien nay ton tai...");
                    break;
                case 11:
                    break;
                case 12:
                    System.out.println("-------------------------------------------------------");
                    System.out.println("|                        TAI KHOAN                    |");
                    System.out.println("-------------------------------------------------------");
                    System.out.format("|%-10s|%-10s|%-15s|%-15s|\n",
                            "Key","Ma NV","Ten dang nhap","Mat khau");
                    System.out.println("-------------------------------------------------------");
                    for (Account i : danhSachTK.getListAccounts())
                        i.xuat();
                    System.out.println("---------------------------------------------------------------------------");
                    break;
                case 13://doc
                    Read();
                    break;
                case 14://luu
                    Save();
                    break;
            }
            System.out.printf("\nEnter to continue...");
            sc.nextLine();
        } while (luachon != 0);

    }

//    public String MenuChonDonVi() {
//        Scanner sc = new Scanner(System.in);
//        bangChon();
//        System.out.printf("Nhap value: ");
//        int chon = sc.nextInt();
//        if (chon == 1) {
//            bangChucVu();
//            System.out.print("Nhap value: ");
//            int temp = sc.nextInt();
//            switch (temp) {
//                case 1:
//                    return "Phuc vu";
//                case 2:
//                    return "Thu ki";
//                case 3:
//                    return "Quan li";
//            }
//        } else if (chon == 2) {
//            return "Khach hang";
//        }
//        return "";
//    }

//    public void GiaoDienNV() {
//        boolean test = true;
//        while (test) {
//            switch (MenuChonDonVi()) {
//                case "Phuc vu":
//                    break;
//                case "Thu ki":
//                    break;
//                case "Quan li":
//
//                    break;
//                default:
//                    test = false;
//                    break;
//            }
//            sc.nextLine();
//        }
//    }
}
