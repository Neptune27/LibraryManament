package Book;

import General.Manage.Management;
import General.Menu.RunnableMenu;
import Interface.ISaveLoad;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLySach {
    private Scanner sc;
    private static ArrayList<Sach> listSach;

    public QuanLySach() {
        listSach = new ArrayList<Sach>();
    }

    public QuanLySach(ArrayList<Sach> listSach) {
        listSach = listSach;
    }
    public void menuQuanLySach(QuanLySach danhSach) throws CloneNotSupportedException {
        RunnableMenu menu = new RunnableMenu("Quản Lý Sách");

//        menu.addSection("Chức năng");
//        menu.add("In ra danh sách mượn sách", QuanLySach::inDanhSach);
//        menu.add("Thêm mượn sách", );
//        menu.add("Thống kê sách mượn", QuanLyMuonSach::thongKe);
//        menu.add("Trả sách", QuanLyMuonSach::traSach);
//        menu.add("Sửa đổi thông tin mượn", QuanLyMuonSach::suaThongTinSachMuon);
//        menu.add("Tìm kiếm sách mượn", QuanLyMuonSach::timKiem);
//
//        menu.addSection("Cập nhật CSDL");
//        menu.add("Lưu", QuanLyMuonSach::ghiVaoCSDL);
//        menu.add("Đọc", QuanLyMuonSach::doctuCSDL);

        menu.show();


//        sc = new Scanner(System.in);
//        int luachon;
//        do {
//            System.out.println("Quản Lý Sách----------------");
//            System.out.println("\tChức năng\t");
//            System.out.println("1.In tất cả các sách");
//            System.out.println("2.Thống kê");
//            System.out.println("3.Thêm sách vào ArrayList");
//            System.out.println("4.Xóa Sách");
//            System.out.println("5.Sửa đổi thông tin sách");
//            System.out.println("6.Tìm kiếm sách");
//            System.out.println("7.Thoát");
//            System.out.println("\tCập nhật CSDL\t");
//            System.out.println("8.Đọc CSDL vào ArrayList");
//            System.out.println("9.Ghi mới từ ArrayList vào CSDL");
//            System.out.println("----------------------------");
//            luachon = Integer.parseInt(sc.nextLine());
//
//            switch (luachon) {
//                case 1:
//                    danhSach.inDanhSach();
//                    break;
//                case 2:
//                    danhSach.thongKe();
//                    break;
//                case 3:
//                    danhSach.themSach();
//                    break;
//                case 4:
//                    danhSach.xoaSach();
//                    break;
//                case 5:
//                    danhSach.suaThongTinSach();
//                    break;
//                case 6:
//                    danhSach.timKiem();
//                    break;
//                case 8:
//                    danhSach.doctuCSDL();
//                    break;
//                case 9:
//                    danhSach.ghiVaoCSDL();
//                    break;
//
//            }
//        } while (luachon != 7);
    }
    public void themSach() throws CloneNotSupportedException {
        Scanner sc  = new Scanner(System.in);
        System.out.print("Thêm bao nhiêu sách: ");
        int n = Integer.parseInt(sc.nextLine());
        Sach sach = null;
        int luachon;
        for (int i=0;i<n;i++){
            System.out.println("Thể Loại: ");
            System.out.println("1.Văn Học Nghệ Thuật");
            System.out.println("2.Truyện Tiểu Thuyết");
            System.out.println("3.Tâm Lý Tâm Linh Tôn Giáo");
            System.out.println("4.Thiếu Nhi");
            luachon = Integer.parseInt(sc.nextLine());
            switch (luachon) {
                case 1:
                    sach = new SachVHNT();
                    ((SachVHNT)sach).setLoai1(((SachVHNT)sach).getLoai());
                    break;
                case 2:
                    sach = new SachTT();
                    ((SachTT)sach).setLoai1(((SachTT)sach).getLoai());

                    break;
                case 3:
                    sach = new SachTL();
                    ((SachTL)sach).setLoai1(((SachTL)sach).getLoai());

                    break;
                case 4:
                    sach = new SachTN();
                    ((SachTN)sach).setLoai1(((SachTN)sach).getLoai());

                    break;
            }
            System.out.print("Mã sách: ");
            sach.setMaSach(sc.nextLine());
            System.out.print("Tên sách: ");
            sach.setTenSach(sc.nextLine());
            System.out.print("Tên tác giả: ");
            sach.setTenTacGia(sc.nextLine());
            System.out.print("Năm xuất bản: ");
            sach.setNxb(Integer.parseInt(sc.nextLine()));
            System.out.println("Độ tuổi cho phép: ");
            sach.setDoTuoi(Integer.parseInt(sc.nextLine()));
            System.out.print("Số Lượng: ");
            sach.setSoLuongMoiCuon(Integer.parseInt(sc.nextLine()));
            if(sach instanceof SachVHNT) {
                ((SachVHNT) sach).setSlTungLoai( ((SachVHNT) sach).getSlTungLoai() + sach.getSoLuongMoiCuon() );
                ((SachVHNT) sach).setSlTungLoai1(((SachVHNT) sach).getSlTungLoai());
            }
            else if (sach instanceof SachTT){
                ((SachTT) sach).setSlTungLoai( ((SachTT) sach).getSlTungLoai() + sach.getSoLuongMoiCuon() );
                ((SachTT) sach).setSlTungLoai1(((SachTT) sach).getSlTungLoai());
            }
            else if (sach instanceof SachTL){
                ((SachTL) sach).setSlTungLoai( ((SachTL) sach).getSlTungLoai() + sach.getSoLuongMoiCuon() );
                ((SachTL) sach).setSlTungLoai1(((SachTL) sach).getSlTungLoai());
            }
            else {
                ((SachTN) sach).setSlTungLoai( ((SachTN) sach).getSlTungLoai() + sach.getSoLuongMoiCuon() );
                ((SachTN) sach).setSlTungLoai1(((SachTN) sach).getSlTungLoai());
            }
            sach.setSoLuong(sach.getSoLuong() + sach.getSoLuongMoiCuon());
            sach.setSoLuong1(sach.getSoLuong());
            System.out.println();
            listSach.add(sach);
        }

    }

    public void thongKe() {
        System.out.println("\t\t-------------Thống Kê-------------\t\t");
        System.out.printf("%-10s%-45s%-30s%-30s%-20s%-20s%-10s\n","Mã sách","Tên sách","Thể loại","Tên tác giả","Năm xuất bản","Độ tuổi","Số lượng");
        for (Sach sach : listSach) {
            if (sach instanceof SachVHNT) {
                System.out.printf("%-10s%-45s%-30s%-30s%-20d%-20d%-10d\n",sach.getMaSach(),sach.getTenSach(),((SachVHNT)sach).getLoai()
                        ,sach.getTenTacGia(),sach.getNxb(),sach.getDoTuoi(),sach.getSoLuongMoiCuon());
            }
            else if (sach instanceof SachTT) {
                System.out.printf("%-10s%-45s%-30s%-30s%-20d%-20d%-10d\n",sach.getMaSach(),sach.getTenSach(),((SachTT)sach).getLoai()
                        ,sach.getTenTacGia(),sach.getNxb(),sach.getDoTuoi(),sach.getSoLuongMoiCuon());
            }
            else if (sach instanceof SachTL) {
                System.out.printf("%-10s%-45s%-30s%-30s%-20d%-20d%-10d\n",sach.getMaSach(),sach.getTenSach(),((SachTL)sach).getLoai()
                        ,sach.getTenTacGia(),sach.getNxb(),sach.getDoTuoi(),sach.getSoLuongMoiCuon());
            }
            else {
                System.out.printf("%-10s%-45s%-30s%-30s%-20d%-20d%-10d\n",sach.getMaSach(),sach.getTenSach(),((SachTN)sach).getLoai()
                        ,sach.getTenTacGia(),sach.getNxb(),sach.getDoTuoi(),sach.getSoLuongMoiCuon());
            }

        }
        System.out.println("\t\t----------------------------------\t\t");

        Sach sach = new SachVHNT();
        System.out.println("Số Lượng sách loại " + ((SachVHNT)sach).getLoai() + ": " + ((SachVHNT) sach).getSlTungLoai());

        sach = new SachTT();
        System.out.println("Số Lượng sách loại " + ((SachTT)sach).getLoai() + ": " + ((SachTT) sach).getSlTungLoai());

        sach = new SachTL();
        System.out.println("Số Lượng sách loại " + ((SachTL)sach).getLoai() + ": " + ((SachTL) sach).getSlTungLoai());

        sach = new SachTN();
        System.out.println("Số Lượng sách loại " + ((SachTN)sach).getLoai() + ": " + ((SachTN) sach).getSlTungLoai());

        System.out.println("Tổng số sách có trong thư viện: " + sach.getSoLuong());


    }

    public void ghiVaoCSDL() {
        File file = new File("./src/Data/Sach.bin");
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (Sach sach : listSach) {
                oos.writeObject(sach);
            }

            oos.flush();
            oos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void doctuCSDL() {
        File file = new File("./src/Book/Sach.txt");
        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            Sach s;
            do {
                s = (Sach) ois.readObject();
                listSach.add(s);
            } while (s != null);
            ois.close();
            is.close();
        } catch (Exception e) {
            System.out.println("Thành công");
        }


        for (Sach sach : listSach) {
            if(sach instanceof SachVHNT) {
                ((SachVHNT) sach).setLoai(((SachVHNT) sach).getLoai1());
                sach.setSoLuong(sach.getSoLuong1());
                ((SachVHNT) sach).setSlTungLoai(((SachVHNT) sach).getSlTungLoai1());
            }
            else if (sach instanceof SachTT){
                ((SachTT) sach).setLoai(((SachTT) sach).getLoai1());
                sach.setSoLuong(sach.getSoLuong1());
                ((SachTT) sach).setSlTungLoai(((SachTT) sach).getSlTungLoai1());
            }
            else if (sach instanceof SachTL){
                ((SachTL) sach).setLoai(((SachTL) sach).getLoai1());
                sach.setSoLuong(sach.getSoLuong1());
                ((SachTL) sach).setSlTungLoai(((SachTL) sach).getSlTungLoai1());
            }
            else {
                ((SachTN) sach).setLoai(((SachTN) sach).getLoai1());
                sach.setSoLuong(sach.getSoLuong1());
                ((SachTN) sach).setSlTungLoai(((SachTN) sach).getSlTungLoai1());
            }
        }
    }

    public void inDanhSach() {
        for (Sach sach : listSach) {
            System.out.println(sach);
        }
    }

    public void xoaSach() {
        System.out.println("Nhập vào mã sách muốn xóa: ");
        String maSach = sc.nextLine();
        boolean kt = false;
        int luachon;
        for (Sach sach : listSach) {
            if (sach.getMaSach().equalsIgnoreCase(maSach)) {
                System.out.println("Sách có " + sach.getSoLuongMoiCuon() + " cuốn bạn muốn xóa bao nhiêu cuốn?");
                luachon = Integer.parseInt(sc.nextLine());
                if (luachon < sach.getSoLuongMoiCuon()) {
                    sach.setSoLuongMoiCuon(sach.getSoLuongMoiCuon() - luachon);
                    sach.setSoLuong(sach.getSoLuong() - luachon);
                    if (sach instanceof SachVHNT) {
                        ((SachVHNT) sach).setSlTungLoai(((SachVHNT) sach).getSlTungLoai() - luachon);
                    }
                    else if (sach instanceof SachTT) {
                        ((SachTT) sach).setSlTungLoai(((SachTT) sach).getSlTungLoai() - luachon);
                    }
                    else if (sach instanceof SachTL) {
                        ((SachTL) sach).setSlTungLoai(((SachTL) sach).getSlTungLoai() - luachon);
                    }
                    else {
                        ((SachTN) sach).setSlTungLoai(((SachTN) sach).getSlTungLoai() - luachon);
                    }
                }
                else {
                    luachon = sach.getSoLuongMoiCuon();
                    sach.setSoLuong(sach.getSoLuong() - luachon);
                    if (sach instanceof SachVHNT) {
                        ((SachVHNT) sach).setSlTungLoai(((SachVHNT) sach).getSlTungLoai() - luachon);
                    }
                    else if (sach instanceof SachTT) {
                        ((SachTT) sach).setSlTungLoai(((SachTT) sach).getSlTungLoai() - luachon);
                    }
                    else if (sach instanceof SachTL) {
                        ((SachTL) sach).setSlTungLoai(((SachTL) sach).getSlTungLoai() - luachon);
                    }
                    else {
                        ((SachTN) sach).setSlTungLoai(((SachTN) sach).getSlTungLoai() - luachon);
                    }
                    this.listSach.remove(sach);
                }
                kt = true;
                break;
            }
        }
        if (kt) {
            System.out.println("Xóa Thành Công!");
        }
        else {
            System.out.println("Không tìm thấy mã sách tương ứng!");
        }
    }

    public void suaThongTinSach() {
        System.out.println("Nhập vào mã sách muốn sửa: ");
        String maSach = sc.nextLine();
        int luachon;
        boolean kt = false;
        for (Sach sach : listSach) {
            if (sach.getMaSach().equalsIgnoreCase(maSach)) {
                kt = true;
                do {
                    System.out.println("Sửa Đổi Thông Tin Sách--------------");
                    System.out.println("1.Mã sách");
                    System.out.println("2.Tên sách");
                    System.out.println("3.Thể loại");
                    System.out.println("4.Tên tác giả");
                    System.out.println("5.Năm xuất bản");
                    System.out.println("6.Độ tuổi cho phép");
                    System.out.println("0.Trở về");
                    System.out.println("------------------------------------");
                    luachon = Integer.parseInt(sc.nextLine());
                    switch (luachon) {
                        case 1:
                            sach.setMaSach(sc.nextLine());
                            break;
                        case 2:
                            sach.setTenSach(sc.nextLine());
                            break;
                        case 3:
                            if (sach instanceof SachVHNT) {
                                ((SachVHNT) sach).setLoai(sc.nextLine());
                            }
                            else if (sach instanceof SachTT) {
                                ((SachTT) sach).setLoai(sc.nextLine());
                            }
                            else if (sach instanceof SachTL) {
                                ((SachTL) sach).setLoai(sc.nextLine());
                            }
                            else {
                                ((SachTN) sach).setLoai(sc.nextLine());
                            }
                            break;
                        case 4:
                            sach.setTenTacGia(sc.nextLine());
                            break;
                        case 5:
                            sach.setNxb(Integer.parseInt(sc.nextLine()));
                            break;
                        case 6:
                            sach.setDoTuoi(Integer.parseInt(sc.nextLine()));
                            break;
                    }
                } while (luachon != 0);
                break;
            }
        }
        if (kt == true) {
            System.out.println("Sửa đổi thành công!");
        }
        else {
            System.out.println("Không tìm thấy mã sách tương ứng!");
        }
    }

    public void timKiem() {
        int luachon;

        do {
            System.out.println("----------Tìm Kiếm----------");
            System.out.println("1.Tìm theo mã sách");
            System.out.println("2.Tìm theo tên sách");
            System.out.println("3.Tìm theo Thể loại");
            System.out.println("4.Tìm theo tên tác giả");
            System.out.println("5.Tìm theo năm xuất bản");
            System.out.println("6.Tìm theo độ tuổi cho phép");
            System.out.println("0.trờ về");
            System.out.println("----------------------------");
            luachon = Integer.parseInt(sc.nextLine());
            switch (luachon) {
                case 1:
                    System.out.print("Nhập mã sách cần tìm: ");
                    String maSach = sc.nextLine();
                    for (Sach sach : listSach) {
                        if (sach.getMaSach().contains(maSach) || sach.getMaSach().equalsIgnoreCase(maSach)) {
                            System.out.println(sach);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Nhập tên sách cần tìm: ");
                    String tenSach = sc.nextLine();
                    for (Sach sach : listSach) {
                        if (sach.getTenSach().equalsIgnoreCase(tenSach) || sach.getMaSach().contains(tenSach)) {
                            System.out.println(sach);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Thể Loại: ");
                    System.out.println("1.Văn Học Nghệ Thuật");
                    System.out.println("2.Truyện Tiểu Thuyết");
                    System.out.println("3.Tâm Lý Tâm Linh Tôn Giáo");
                    System.out.println("4.Thiếu Nhi");
                    luachon = Integer.parseInt(sc.nextLine());
                    for (Sach sach : listSach) {
                        if (luachon == 1 && sach.getMaSach().contains("VH")){
                            System.out.println(sach);
                        }
                        else if (luachon == 2 && sach.getMaSach().contains("TT")) {
                            System.out.println(sach);
                        }
                        else if (luachon == 3 && sach.getMaSach().contains("TL")) {
                            System.out.println(sach);
                        }
                        else if (luachon == 4 && sach.getMaSach().contains("TN")){
                            System.out.println(sach);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Nhập tên tác giả cần tìm: ");
                    String tenTacGia = sc.nextLine();
                    for (Sach sach : listSach) {
                        if (sach.getTenTacGia().equalsIgnoreCase(tenTacGia) ||sach.getTenTacGia().contains(tenTacGia)) {
                            System.out.println(sach);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Nhập năm xuất bản cần tìm: ");
                    int nxb = Integer.parseInt(sc.nextLine());
                    for (Sach sach : listSach) {
                        if (sach.getNxb() == nxb) {
                            System.out.println(sach);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Nhập độ tuổi: ");
                    int doTuoi = Integer.parseInt(sc.nextLine());
                    for (Sach sach : listSach) {
                        if (sach.getDoTuoi() == doTuoi) {
                            System.out.println(sach);
                        }
                    }
            }
        } while (luachon != 0);


    }

    @Override
    public void generateMenu() {

    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }
}
