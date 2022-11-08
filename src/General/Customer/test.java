package General.Customer;

import java.io.Serializable;

public class test implements Serializable {
    public static void main(String[] args) {
        QuanLyKhachHang ql = new QuanLyKhachHang();
        ql.addCus();
        QuanLyKhachHang.writeDB();
    }
}
