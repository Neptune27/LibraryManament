package Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyMuonSach {
    private ArrayList<PhieuMuonSach> listMuon;

    public QuanLyMuonSach() {
    }

    public QuanLyMuonSach(ArrayList<PhieuMuonSach> listMuon) {
        this.listMuon = listMuon;
    }

    public ArrayList<PhieuMuonSach> getListMuon() {
        return listMuon;
    }

    public void setListMuon(ArrayList<PhieuMuonSach> listMuon) {
        this.listMuon = listMuon;
    }

    public void menuQuanLyMuonSach(QuanLyMuonSach danhSach) {
        Scanner sc = new Scanner(System.in);
        int luachon;
        do {
            System.out.println("Quản Lý Mượn Sách----------------");
            System.out.println("\tChức năng\t");
            System.out.println("1.Thêm mượn sách");
            System.out.println("2.In ra danh sách mượn sách");
            System.out.println("3.Thống kê sách mượn");
            System.out.println("4.Trả sách");
            System.out.println("5.Sửa đổi thông tin  mượn");
            System.out.println("6.Tìm kiếm sách mượn");
            System.out.println("7.Thoát");
            System.out.println("\tCập nhật CSDL\t");
            System.out.println("8.Đọc CSDL vào ArrayList");
            System.out.println("9.Ghi mới từ ArrayList vào CSDL");
            System.out.println("----------------------------");
            luachon = Integer.parseInt(sc.nextLine());

            switch (luachon) {
                case 1:
                    danhSach.themSachMuon();
                    break;
                case 2:
                    danhSach.inSachMuon();
                    break;
                case 3:
                    danhSach.thongKe();
                    break;
                case 4:
                    danhSach.traSach();
                    break;
                case 5:
                    danhSach.suaThongTinSachMuon();
                    break;
                case 6:
                    danhSach.timKiem();
                    break;
                case 8:
                    danhSach.doctuCSDL();
                    break;
                case 9:
                    danhSach.ghiVaoCSDL();
                    break;

            }
        } while (luachon != 7);

    }



    private void themSachMuon() {
        Scanner sc  = new Scanner(System.in);
        PhieuMuonSach phieuMuonSach = new PhieuMuonSach();
        System.out.print("Mượn bao nhiêu sách: ");
        int n = Integer.parseInt(sc.nextLine());

    }

    private void inSachMuon() {

    }

    private void thongKe() {
    }

    private void traSach() {
    }

    private void suaThongTinSachMuon() {
    }

    private void timKiem() {
    }

    public void ghiVaoCSDL() {
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
    public void doctuCSDL() {
        File file = new File("./src/Book/SachMuon.txt");
        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            PhieuMuonSach phieuMuonSach;
            while (true){
                phieuMuonSach = (PhieuMuonSach) ois.readObject();
                this.listMuon.add(phieuMuonSach);
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
