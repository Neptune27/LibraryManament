package Book;

import General.Menu.RunnableMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyMuonSach {
    static private ArrayList<PhieuMuonSach> listMuon;

    public QuanLyMuonSach() {
    }

    public QuanLyMuonSach(ArrayList<PhieuMuonSach> listMuon) {
        QuanLyMuonSach.listMuon = listMuon;
    }

    public static ArrayList<PhieuMuonSach> getListMuon() {
        return listMuon;
    }

    public static void setListMuon(ArrayList<PhieuMuonSach> listMuon) {
        QuanLyMuonSach.listMuon = listMuon;
    }

    public static void menuQuanLyMuonSach() {
        RunnableMenu menu = new RunnableMenu("Quản Lý Mượn Sách");

        menu.addSection("Chức năng");
        menu.add("Thêm mượn sách", QuanLyMuonSach::themSachMuon);
        menu.add("In ra danh sách mượn sách", QuanLyMuonSach::inSachMuon);
        menu.add("Thống kê sách mượn", QuanLyMuonSach::thongKe);
        menu.add("Trả sách", QuanLyMuonSach::traSach);
        menu.add("Sửa đổi thông tin mượn", QuanLyMuonSach::suaThongTinSachMuon);
        menu.add("Tìm kiếm sách mượn", QuanLyMuonSach::timKiem);

        menu.addSection("Cập nhật CSDL");
        menu.add("Lưu", QuanLyMuonSach::ghiVaoCSDL);
        menu.add("Đọc", QuanLyMuonSach::doctuCSDL);

        menu.show();

//        Scanner sc = new Scanner(System.in);
//        int luachon;
//        do {
//            System.out.println("Quản Lý Mượn Sách----------------");
//            System.out.println("\tChức năng\t");
//            System.out.println("1.Thêm mượn sách");
//            System.out.println("2.In ra danh sách mượn sách");
//            System.out.println("3.Thống kê sách mượn");
//            System.out.println("4.Trả sách");
//            System.out.println("5.Sửa đổi thông tin  mượn");
//            System.out.println("6.Tìm kiếm sách mượn");
//            System.out.println("7.Thoát");
//            System.out.println("\tCập nhật CSDL\t");
//            System.out.println("8.Đọc CSDL vào ArrayList");
//            System.out.println("9.Ghi mới từ ArrayList vào CSDL");
//            System.out.println("----------------------------");
//            luachon = Integer.parseInt(sc.nextLine());
//
//            switch (luachon) {
//                case 1:
//                    QuanLyMuonSach.themSachMuon();
//                    break;
//                case 2:
//                    QuanLyMuonSach.inSachMuon();
//                    break;
//                case 3:
//                    QuanLyMuonSach.thongKe();
//                    break;
//                case 4:
//                    QuanLyMuonSach.traSach();
//                    break;
//                case 5:
//                    QuanLyMuonSach.suaThongTinSachMuon();
//                    break;
//                case 6:
//                    QuanLyMuonSach.timKiem();
//                    break;
//                case 8:
//                    QuanLyMuonSach.doctuCSDL();
//                    break;
//                case 9:
//                    QuanLyMuonSach.ghiVaoCSDL();
//                    break;
//
//            }
//        } while (luachon != 7);

    }



    private static void themSachMuon() {
        Scanner sc  = new Scanner(System.in);
        PhieuMuonSach phieuMuonSach = new PhieuMuonSach();
        System.out.print("Mượn bao nhiêu sách: ");
        int n = Integer.parseInt(sc.nextLine());

    }

    private static void inSachMuon() {

    }

    private static void thongKe() {
    }

    private static void traSach() {
    }

    private static void suaThongTinSachMuon() {
    }

    private static void timKiem() {
    }

    public static void ghiVaoCSDL() {
        File file = new File("./src/Book/Sach.txt");
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (PhieuMuonSach phieuMuonSach : listMuon) {
                oos.writeObject(phieuMuonSach);
            }

            oos.flush();
            oos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void doctuCSDL() {
        File file = new File("./src/Book/SachMuon.txt");
        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            PhieuMuonSach phieuMuonSach;
            while (true){
                phieuMuonSach = (PhieuMuonSach) ois.readObject();
                listMuon.add(phieuMuonSach);
                if(phieuMuonSach == null) break;
            }
            ois.close();
            is.close();
        } catch (Exception e) {
            System.out.println("Thành công");
        }

        for (PhieuMuonSach phieuMuonSach : listMuon) {

        }
    }
}
