package Book;

import General.Menu.RunnableMenu;


public class main {
    public static void main(String[] args) throws CloneNotSupportedException {

        RunnableMenu menu = new RunnableMenu("MENU");
        QuanLySach qlSach = new QuanLySach();

        menu.add("Quan ly Sach Toàn Thu Viện", ()->qlSach.menuQuanLySach(qlSach));
        menu.add("Quan ly Sách Mượn", QuanLyMuonSach::menuQuanLyMuonSach);
        menu.add("Quan ly Sach hien co", ()->{});

        menu.show();

//        Scanner sc = new Scanner(System.in);
////        QuanLyMuonSach qlmSach = new QuanLyMuonSach();
//        int luachon;
//        do {
//            System.out.println("MENU------------------");
//            System.out.println("1.Quản Lý Sách Toàn Thư Viện");
//            System.out.println("2.Quản Lý Sách Mượn");
//            System.out.println("3.Quản Lý Sách hiện có");
//            System.out.println("4.Thoát");
//            System.out.println("----------------------");
//            luachon = Integer.parseInt(sc.nextLine());
//            switch (luachon) {
//                case 1:
//                    qlSach.menuQuanLySach(qlSach);
//                    break;
//                case 2:
//                    QuanLyMuonSach.menuQuanLyMuonSach();
//                    break;
//                case 3: break;
//            }
//        } while(luachon != 4);
//        qlSach.menuQuanLySach(qlSach);
    }

}
