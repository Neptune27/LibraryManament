package Example.PERSONNEL_MANAGER;

import java.util.Scanner;

public class main {
    static void dangNhap() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("|                   XIN CHAO BAN DEN VOI THU VIEN                   |");
        System.out.println("|-------------------------------------------------------------------|");
        System.out.format("|%-4s|%-62s|\n", " 1.", "Toi la Quan Li");
        System.out.format("%-68s|\n", "|----|");
        System.out.format("|%-4s|%-62s|\n", " 2.", "Toi la Thu Ki");
        System.out.format("%-68s|\n", "|----|");
        System.out.format("|%-4s|%-62s|\n", " 3.", "Toi la Nhan Vien");
        System.out.format("%-68s|\n", "|----|");
        System.out.format("|%-4s|%-62s|\n", " 4.", "Toi la Khach Hang");
        System.out.format("%-68s|\n", "|----|");
        System.out.format("|%-4s|%-62s|\n", " 0.", "Thoat");
        System.out.println("---------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner so = new Scanner(System.in);
        Scanner chu = new Scanner(System.in);
        boolean test = true;
        //tao tai khoan admin mặc định
        QuanLi quanLi = new QuanLi();
        ThuKi thuKi = new ThuKi();
        NhanVien nhanVien = new NhanVien();
        User user = new User();

        Account admin = new Account("admin","Quan li","admin","admin");
        quanLi.getDanhSachTK().ThemTK(admin);

        int chon;
        while (test) {
            dangNhap();
            System.out.printf("Nhap Value: ");
            chon = so.nextInt();
            switch (chon) {
                case 1: //quan li
                    if(quanLi.getDanhSachTK().dangnhap( quanLi.getDanhSachTK().getListAccounts(),"Quan li"))
                        quanLi.menu();
                    else {
                        System.out.println("ERROR SIGN IN");
                        System.out.printf("\nEnter to continue...");
                        chu.nextLine();
                    }
                    break;
                case 2://thu ki
                    if(quanLi.getDanhSachTK().dangnhap(  quanLi.getDanhSachTK().getListAccounts(),"Thu ki")) {
                        thuKi.menu(quanLi.getDanhSachNV(), quanLi.getDanhSachTK().getListAccounts(),
                                quanLi.getDanhSachTKKH().getListAccounts(),  quanLi.getDanhSachTK().getLuuTenDangNhap());
                    }
                    else {
                        System.out.println("ERROR SIGN IN");
                        System.out.printf("\nEnter to continue...");
                        chu.nextLine();
                    }
                    break;
                case 3://PV
                    if( quanLi.getDanhSachTK().dangnhap( quanLi.getDanhSachTK().getListAccounts(),"Phuc vu"))
                        nhanVien.menu(quanLi.getDanhSachNV(), quanLi.getDanhSachTK().getLuuTenDangNhap());
                    else {
                        System.out.println("ERROR SIGN IN");
                        System.out.printf("\nEnter to continue...");
                        chu.nextLine();
                    }

                    break;
                case 4://KHang

                    break;
                case 0:
                    test = false;
                    break;
            }

        }

    }
}
