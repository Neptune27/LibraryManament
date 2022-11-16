//package Book;
//
//import General.Menu.RunnableMenu;
//import User.Errors.UsernameExistException;
//
//import javax.swing.text.Style;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
//public class main {
//    public static void main(String[] args) throws CloneNotSupportedException, UsernameExistException {
//        khoiTaoCustomer();
//        khoiTaoStaff();
//        RunnableMenu menu = new RunnableMenu("MENU");
//        menu.add("Quan ly Sach Toàn Thu Viện", QuanLySach::menuQuanLySach);
//        menu.add("Quan ly Sách Mượn", QuanLyMuonSach::menuQuanLyMuonSach);
//        menu.add("Quan ly Sach hien co", QuanLySachHienCo::menuQuanLySachHienCo);
//        menu.show();
//    }
//    public static void khoiTaoCustomer() throws UsernameExistException {
//        Scanner sc = new Scanner(System.in);
//        ListCustomer.listCustomer = new ArrayList<>();
//        Customer customer1 = new Customer("123","Nguyễn Hoàng Tiến",19,"tiennguyen","1702");
//        Customer customer2 = new Customer("456","Trần Gia Hân",23,"han19","1053");
//        Customer customer3 = new Customer("789","Ngô Hoàng Tín",16,"tin68","4867");
//        ListCustomer.listCustomer.add(customer1);
//        ListCustomer.listCustomer.add(customer2);
//        ListCustomer.listCustomer.add(customer3);
//    }
//    public static void khoiTaoStaff() throws UsernameExistException {
//        Scanner sc = new Scanner(System.in);
//        ListStaff.listStaff = new ArrayList<>() ;
//        Staff staff1 = new Staff("nv1","Phạm Minh Nhựt",25,"tiennguyen","1702");
//        Staff staff2 = new Staff("nv2","Phan Ngọc Tuấn",30,"tiennguyen","1702");
//        Staff staff3 = new Staff("nv3","Võ Tấn Phát",28,"tiennguyen","1702");
//        ListStaff.listStaff.add(staff1);
//        ListStaff.listStaff.add(staff2);
//        ListStaff.listStaff.add(staff3);
//    }
//}
