//package Book;
//
//import General.Menu.RunnableMenu;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class QuanLyMuonSach {
//    private static final ArrayList<PhieuMuonSach> listMuon = new ArrayList<>();
//
//
//    public static void menuQuanLyMuonSach() {
//        RunnableMenu menu = new RunnableMenu("Quản Lý Mượn Sách");
//
//        menu.addSection("Chức năng");
//        menu.add("Thêm mượn sách", QuanLyMuonSach::themSachMuon);
//        menu.add("In ra các sách được mượn", QuanLyMuonSach::inSachMuon);
//        menu.add("Thống kê sách mượn", QuanLyMuonSach::thongKe);
//        menu.add("Trả sách", QuanLyMuonSach::traSach);
//        menu.add("Tìm kiếm sách mượn", QuanLyMuonSach::timKiem);
//        menu.addSection("Cập nhật CSDL");
//        menu.add("Lưu", QuanLyMuonSach::ghiVaoCSDL);
//        menu.add("Đọc", QuanLyMuonSach::doctuCSDL);
//        menu.show();
//
//    }
//
//
//
//
//    private static void themSachMuon() {
//        Scanner sc = new Scanner(System.in);
//        PhieuMuonSach phieuMuonSach = new PhieuMuonSach();
//        System.out.print("Mã Khách Hàng: ");
//        String maKhachHang = sc.nextLine();
//        boolean kt = false;
//        for (Customer customer : ListCustomer.listCustomer) {
//            if (customer.getId().equalsIgnoreCase(maKhachHang)) {
//                phieuMuonSach.setMaKhachHang(customer.getId());
//                kt = true;
//                break;
//            }
//        }
//
//        if (!kt) {
//            System.out.println("Không tìm thấy khách hàng tương ứng!");
//        } else {
//            kt = false;
//            System.out.print("Nhập mã sách muốn mượn: ");
//            String maSach = sc.nextLine();
//            System.out.print("Mượn bao nhiêu sách: ");
//            int soSach = Integer.parseInt(sc.nextLine());
//            for (Sach sach : QuanLySach.listSach) {
//                if (sach.getMaSach().equalsIgnoreCase(maSach) && (sach.getSoLuongMoiCuon() - sach.getSoSachMuon()) >= soSach) {
//                    sach.setDuocMuon(true);
//                    sach.setSoSachMuon(sach.getSoSachMuon() + soSach);
//                    phieuMuonSach.setMaSach(maSach);
//                    phieuMuonSach.setSoLuong(soSach);
//                    kt = true;
//                    break;
//                }
//            }
//            if (!kt) {
//                System.out.println("Sách không tồn tại hoặc đã hết!");
//            } else {
//                kt = false;
//                do {
//                    System.out.print("Nhập mã nhân viên: ");
//                    String maNhanVien = sc.nextLine();
//                    for (Staff staff : ListStaff.listStaff) {
//                        if (staff.getId().equalsIgnoreCase(maNhanVien)) {
//                            phieuMuonSach.setMaNhanVien(staff.getId());
//                            kt = true;
//                            break;
//                        }
//                    }
//                    if (!kt) System.out.println("Không tìm thấy nhân viên tương ứng.Vui lòng nhập lại!");
//                } while (!kt);
//                System.out.print("Ngày mượn: ");
//                NgayThangNam ngayThangNam = new NgayThangNam(sc.nextInt(), sc.nextInt(), sc.nextInt());
//                phieuMuonSach.setNgayMuon(ngayThangNam);
//
//                System.out.print("Ngày trả: ");
//                ngayThangNam = new NgayThangNam(sc.nextInt(), sc.nextInt(), sc.nextInt());
//                phieuMuonSach.setNgayTra(ngayThangNam);
//                listMuon.add(phieuMuonSach);
//            }
//        }
//
//
//    }
//
//    private static void inSachMuon() {
//        for (Sach sach : QuanLySach.listSach) {
//            if (sach.isDuocMuon()) {
//                System.out.println(sach.toString() + ", soLuongSachMuon=" + sach.getSoSachMuon() + ", soLuongSachConLai="
//                        + (sach.getSoLuongMoiCuon() - sach.getSoSachMuon()));
//            }
//        }
//        System.out.println();
//    }
//
//    private static void thongKe() {
//        System.out.println("\t\t-------------Thống Kê-------------\t\t");
//        System.out.printf("%-10s%-45s%-30s%-30s%-20s%-10s%-10s\n", "Mã sách", "Tên sách", "Thể loại", "Tên tác giả", "Năm xuất bản", "Độ tuổi", "Số lượng");
//        for (Sach sach : QuanLySach.listSach) {
//            if (sach.isDuocMuon()) {
//                if (sach instanceof SachVHNT) {
//                    System.out.printf("%-10s%-45s%-30s%-30s%-20d%-10d%-10d\n", sach.getMaSach(), sach.getTenSach(), ((SachVHNT) sach).getLoai()
//                            , sach.getTenTacGia(), sach.getNxb(), sach.getDoTuoi(),sach.getSoSachMuon());
//                } else if (sach instanceof SachTT) {
//                    System.out.printf("%-10s%-45s%-30s%-30s%-20d%-10d%-10d\n", sach.getMaSach(), sach.getTenSach(), ((SachTT) sach).getLoai()
//                            , sach.getTenTacGia(), sach.getNxb(), sach.getDoTuoi(),sach.getSoSachMuon());
//                } else if (sach instanceof SachTL) {
//                    System.out.printf("%-10s%-45s%-30s%-30s%-20d%-10d%-10d\n", sach.getMaSach(), sach.getTenSach(), ((SachTL) sach).getLoai()
//                            , sach.getTenTacGia(), sach.getNxb(), sach.getDoTuoi(),sach.getSoSachMuon());
//                } else {
//                    System.out.printf("%-10s%-45s%-30s%-30s%-20d%-10d%-10d\n", sach.getMaSach(), sach.getTenSach(), ((SachTN) sach).getLoai()
//                            , sach.getTenTacGia(), sach.getNxb(), sach.getDoTuoi(),sach.getSoSachMuon());
//                }
//            }
//
//
//        }
//        System.out.println("\t\t-------------Thống Kê-------------\t\t");
//        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s\n", "Mã Khách Hàng", "Mã Nhân Viên", "Mã Sách", "Số Lượng", "Ngày Mượn", "Ngày trả");
//        for (PhieuMuonSach phieuMuonSach : listMuon) {
//            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s\n", phieuMuonSach.getMaKhachHang(), phieuMuonSach.getMaNhanVien()
//                    , phieuMuonSach.getMaSach(), phieuMuonSach.getSoLuong(), phieuMuonSach.getNgayMuon(), phieuMuonSach.getNgayTra());
//        }
//        System.out.println("\t\t----------------------------------\t\t");
//    }
//
//    private static void traSach() {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Nhập mã khách hàng: ");
//        String maKhachHang = sc.nextLine();
//        System.out.print("Nhập mã sách muốn trả: ");
//        String maSach = sc.nextLine();
//        boolean kt = false;
//        int soSachTra = 0;
//        for (PhieuMuonSach phieuMuonSach : listMuon) {
//            if (phieuMuonSach.getMaKhachHang().equalsIgnoreCase(maKhachHang) && phieuMuonSach.getMaSach().equalsIgnoreCase(maSach)) {
//                listMuon.remove(phieuMuonSach);
//                soSachTra = phieuMuonSach.getSoLuong();
//                kt = true;
//                break;
//            }
//        }
//        if (!kt) {
//            System.out.println("Không tìm thấy khách hàng tương ứng!");
//        } else {
//            for (Sach sach : QuanLySach.listSach) {
//                if (sach.getMaSach().equalsIgnoreCase(maSach)) {
//                    sach.setSoSachMuon(sach.getSoSachMuon() - soSachTra);
//                    if (sach.getSoSachMuon() == 0) {
//                        sach.setDuocMuon(true);
//                    }
//                }
//            }
//        }
//    }
//
//        private static void timKiem() {
//            Scanner sc = new Scanner(System.in);
//            int luachon;
//            do {
//                System.out.println("----------Tìm Kiếm----------");
//                System.out.println("1.Tìm theo mã sách");
//                System.out.println("2.Tìm theo mã khách hàng");
//                System.out.println("0.trờ về");
//                System.out.println("----------------------------");
//                luachon = Integer.parseInt(sc.nextLine());
//                switch (luachon) {
//                    case 1:
//                        System.out.print("Nhập mã sách cần tìm: ");
//                        String maSach = sc.nextLine();
//                        for (PhieuMuonSach phieuMuonSach : listMuon) {
//                            if (phieuMuonSach.getMaSach().contains(maSach) || phieuMuonSach.getMaSach().equalsIgnoreCase(maSach)) {
//                                System.out.println(phieuMuonSach);
//                            }
//                        }
//                        break;
//                    case 2:
//                        System.out.println("Nhập mã khách hàng cần tìm: ");
//                        String maKhachHang = sc.nextLine();
//                        for (PhieuMuonSach phieuMuonSach : listMuon) {
//                            if (phieuMuonSach.getMaKhachHang().equalsIgnoreCase(maKhachHang) || phieuMuonSach.getMaKhachHang().contains(maKhachHang)) {
//                                System.out.println(phieuMuonSach);
//                            }
//                        }
//                        break;
//                }
//            } while (luachon != 0);
//
//
//
//        }
//
//        public static void ghiVaoCSDL() {
//            File file = new File("./src/Data/SachMuon.bin");
//            try {
//                OutputStream os = new FileOutputStream(file);
//                ObjectOutputStream oos = new ObjectOutputStream(os);
//                for (PhieuMuonSach phieuMuonSach : listMuon) {
//                    oos.writeObject(phieuMuonSach);
//                }
//
//                oos.flush();
//                oos.close();
//                os.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//        public static void doctuCSDL() {
//            File file = new File("./src/Data/SachMuon.bin");
//            try {
//                InputStream is = new FileInputStream(file);
//                ObjectInputStream ois = new ObjectInputStream(is);
//                PhieuMuonSach phieuMuonSach;
//                while (true) {
//                    phieuMuonSach = (PhieuMuonSach) ois.readObject();
//                    listMuon.add(phieuMuonSach);
//                    if (phieuMuonSach == null) break;
//                }
//                ois.close();
//                is.close();
//            } catch (Exception e) {
//                System.out.println("Thành công");
//            }
//
//        }
//    }
