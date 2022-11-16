package Example.PERSONNEL_MANAGER;

import java.util.ArrayList;

public class NhanVien extends ConNguoi implements Luong {
    private String maNV;
    private String xepLoai;
    private float soNgayNghi;
    private String caLam;
//    Cua Permission
    private String chucVu;

    public NhanVien() {
    }

    public NhanVien(String maNV, String chucVu, String hoTen, String gioiTinh, int tuoi, String xepLoai,
                    DiaChi diaChi, String soDt, float soNgayNghi, String caLam) {
        super(hoTen, gioiTinh, tuoi, diaChi, soDt);
        this.maNV = maNV;
        this.chucVu = chucVu;
        this.soNgayNghi = soNgayNghi;
        this.caLam = caLam;
        this.xepLoai = xepLoai;
    }

    @Override
    public String getHoTen() {
        return super.getHoTen();
    }

    public String getMaNV() {
        return maNV;
    }


    public String getXepLoai() {
        return xepLoai;
    }


    public float getSoNgayNghi() {
        return soNgayNghi;
    }

    public String getCaLam() {
        return caLam;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }


    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }


    public void setSoNgayNghi(float soNgayNghi) {
        this.soNgayNghi = soNgayNghi;
    }

    public void setCaLam(String caLam) {
        this.caLam = caLam;
    }


    static void bangCaLam() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("|                         CHON CA MUON LAM                         |");
        System.out.println("--------------------------------------------------------------------");
        System.out.format("|%-22s%-22s%-22s|\n", "1. Lam buoi sang", "2. Lam buoi chieu", "3. Lam ca 2 buoi");
        System.out.println("--------------------------------------------------------------------");
        ;
    }

    static void bangChucVu() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("|                       CHON CHUC VU NHAN VIEN                     |");
        System.out.println("--------------------------------------------------------------------");
        System.out.format("|%-22s%-22s%-22s|\n", "1. Phuc vu", "2. Thu ky", "3. Quan li");
        System.out.println("--------------------------------------------------------------------");
        ;
    }

    @Override
    public boolean nhap(ArrayList<NhanVien> listNV) {
        int tmp = 0;
        if (listNV.isEmpty()) {
            nhapListNULL(listNV);
            return true;
        }
        System.out.printf("Nhap ma nhan vien: ");
        maNV = scText.nextLine();
        while (true) {
            tmp=0;
            for (int i = 0; i < listNV.size(); i++) {
                if (listNV.get(i).getMaNV().equalsIgnoreCase(maNV)) {
                    System.out.println("Ma nhan vien da ton tai");
                    System.out.println("Yeu cau nhap lai");
                    System.out.printf("Nhap lai ma nhan vien: ");
                    setMaNV(scText.nextLine());
                    tmp=0;
                    continue;
                }
                tmp++;
            }
            if (tmp==listNV.size())
                break;
        }
        super.nhap(listNV);
        int choose;
        while (true) {
            bangChucVu();
            System.out.println("Chuc vu la gi?  ");
            choose = scInt.nextInt();
            if (choose == 1) {
                chucVu = "Phuc vu";
                break;
            } else if (choose == 2) {
                chucVu = "Thu ki";
                break;
            } else if (choose == 3) {
                chucVu = "Quan li";
                break;
            } else
                System.out.println("Yeu cau ban chon khong co trong bang tren...");
        }
        while (true) {
            bangCaLam();
            System.out.println("Chon ca lam viec: ");
            choose = scInt.nextInt();
            if (choose == 1) {
                caLam = "Lam buoi sang";
                break;
            } else if (choose == 2) {
                caLam = "Lam buoi chieu";
                break;
            } else if (choose == 3) {
                caLam = "Lam ca hai buoi";
                break;
            } else
                System.out.println("Yeu cau ban chon khong co trong bang lam viec");
        }
        System.out.printf("Nhap so ngay nghi :");
        soNgayNghi = Float.parseFloat(scText.nextLine());
        if (soNgayNghi <= 1)
            xepLoai = "A";
        else if (soNgayNghi <= 2)
            xepLoai = "B";
        else
            xepLoai = "C";
        return true;
    }
    @Override
    public boolean nhapListNULL(ArrayList<NhanVien> listNV) {
        int tmp = 0;
        System.out.printf("Nhap ma nhan vien: ");
        maNV = scText.nextLine();
        super.nhapListNULL(listNV);
        int choose;
        while (true) {
            bangChucVu();
            System.out.println("Chuc vu la gi?  ");
            choose = scInt.nextInt();
            if (choose == 1) {
                chucVu = "Phuc vu";
                break;
            } else if (choose == 2) {
                chucVu = "Thu ki";
                break;
            } else if (choose == 3) {
                chucVu = "Quan li";
                break;
            } else
                System.out.println("Yeu cau ban chon khong co trong bang tren...");
        }
        while (true) {
            bangCaLam();
            System.out.println("Chon ca lam viec: ");
            choose = scInt.nextInt();
            if (choose == 1) {
                caLam = "Lam buoi sang";
                break;
            } else if (choose == 2) {
                caLam = "Lam buoi chieu";
                break;
            } else if (choose == 3) {
                caLam = "Lam ca hai buoi";
                break;
            } else
                System.out.println("Yeu cau ban chon khong co trong bang lam viec");
        }
        System.out.printf("Nhap so ngay nghi :");
        soNgayNghi = Float.parseFloat(scText.nextLine());
        if (soNgayNghi <= 1)
            xepLoai = "A";
        else if (soNgayNghi <= 2)
            xepLoai = "B";
        else
            xepLoai = "C";
        return true;
    }
    @Override
    public void xuat() {
        System.out.format("|%-10s", chucVu);
        System.out.format("|%-10s", maNV);
        super.xuat();
        System.out.format("|%-10s|%-15s|%-13.1f|\n", xepLoai, caLam, soNgayNghi);
    }

    @Override
    public float luong() {
        return (30 - getSoNgayNghi()) * 20000;
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
        System.out.format("|%-10s|%-10s|%-18s|%-10.2f|%-10.2f|%-10.2f|\n", chucVu, maNV, getHoTen(), luong(), thuong(), tongLuong());
    }

    public void menu(ArrayList<NhanVien> listNV, String maNV) {
        int luachon;
        do {
            System.out.println("\n------------Nhan Vien Phuc Vu----------------");
            System.out.println("\tChức năng\t");
            System.out.println("1.In ra thong tin cua minh");
            System.out.println("2.Xem luong cua minh");
            System.out.println("3.Dang ki muon sach");
            System.out.println("4.In hoa don muon sach");
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
                        if (maNV.equalsIgnoreCase(i.getMaNV())) {
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
                        if (maNV.equalsIgnoreCase(i.getMaNV())) {
                            i.xuatLuong();
                            break;
                        }
                    System.out.println("---------------------------------------------------------------------------");
                    break;
                case 3:
                    //order sach
                    break;
                case 4:
                    //In hoa don
                    break;
            }
        } while (luachon != 0);

    }
}
