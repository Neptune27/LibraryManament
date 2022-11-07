package Book;

import General.Menu.RunnableMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLySachHienCo {

    public static void menuQuanLySachHienCo() throws CloneNotSupportedException {
        RunnableMenu menu = new RunnableMenu("Quản Lý Sách Hiện Có");

        menu.addSection("Chức năng");
        menu.add("Thống kê", QuanLySachHienCo::thongKe);
        menu.show();

    }
    public static void thongKe() {
        System.out.println("\t\t-------------Thống Kê-------------\t\t");
        System.out.printf("%-10s%-45s%-30s%-30s%-20s%-20s%-30s\n","Mã sách","Tên sách","Thể loại","Tên tác giả","Năm xuất bản","Độ tuổi","Số lượng Hiện Có");
        for (Sach sach : QuanLySach.listSach) {
            if (sach instanceof SachVHNT) {
                System.out.printf("%-10s%-45s%-30s%-30s%-20d%-20d%-30d\n",sach.getMaSach(),sach.getTenSach(),((SachVHNT)sach).getLoai()
                        ,sach.getTenTacGia(),sach.getNxb(),sach.getDoTuoi(),sach.getSoLuongMoiCuon() - sach.getSoSachMuon());
            }
            else if (sach instanceof SachTT) {
                System.out.printf("%-10s%-45s%-30s%-30s%-20d%-20d%-30d\n",sach.getMaSach(),sach.getTenSach(),((SachTT)sach).getLoai()
                        ,sach.getTenTacGia(),sach.getNxb(),sach.getDoTuoi(),sach.getSoLuongMoiCuon() - sach.getSoSachMuon());
            }
            else if (sach instanceof SachTL) {
                System.out.printf("%-10s%-45s%-30s%-30s%-20d%-20d%-30d\n",sach.getMaSach(),sach.getTenSach(),((SachTL)sach).getLoai()
                        ,sach.getTenTacGia(),sach.getNxb(),sach.getDoTuoi(),sach.getSoLuongMoiCuon() - sach.getSoSachMuon());
            }
            else {
                System.out.printf("%-10s%-45s%-30s%-30s%-20d%-20d%-30d\n",sach.getMaSach(),sach.getTenSach(),((SachTN)sach).getLoai()
                        ,sach.getTenTacGia(),sach.getNxb(),sach.getDoTuoi(),sach.getSoLuongMoiCuon() - sach.getSoSachMuon());
            }

        }
        System.out.println("\t\t----------------------------------\t\t");
    }

}
