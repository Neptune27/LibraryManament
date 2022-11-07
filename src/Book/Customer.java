package Book;

import User.EPermission;
import User.User;
import User.Errors.UsernameExistException;


public class Customer extends User {
    public Customer(String id, String name, int age, String username, String password) throws UsernameExistException {
        super(id, name, age, username, password, EPermission.CUSTOMER);
    }

    public void sachCoTheMuon() {};

//    public String getMaKhachHang() {
//        return maKhachHang;
//    }
//
//    public void setMaKhachHang(String maKhachHang) {
//        this.maKhachHang = maKhachHang;
//    }
//
//    public String getTenKhachHang() {
//        return tenKhachHang;
//    }
//
//    public void setTenKhachHang(String tenKhachHang) {
//        this.tenKhachHang = tenKhachHang;
//    }
//
//    public int getTuoi() {
//        return tuoi;
//    }
//
//    public void setTuoi(int tuoi) {
//        this.tuoi = tuoi;
//    }

    public void muonSach(){

    };

    @Override
    public void generateMenu() {

    }
}
