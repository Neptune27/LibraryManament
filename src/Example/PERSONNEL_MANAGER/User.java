package Example.PERSONNEL_MANAGER;

import java.util.ArrayList;

public class User extends  ConNguoi{
    static ArrayList<User> danhsachKH = new ArrayList<>();
    private String maUser;
    public User(){
    }
    public User(String maUser,String hoTen, String gioiTinh,int tuoi,
                DiaChi diaChi, String soDt) {
        super(hoTen,gioiTinh,tuoi,diaChi,soDt);
        this.maUser = maUser;
    }

    public ArrayList<User> getDanhsachKH() {
        return danhsachKH;
    }

    public void setDanhsachKH(ArrayList<User> danhsachKH) {
        User.danhsachKH = danhsachKH;
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public void nhapUser(ArrayList<User> listKH, ArrayList<NhanVien> listNV) {
        ConNguoi conNguoi = new ConNguoi();
        int temp=0;
        System.out.printf("Nhap ma khach hang : ");
        maUser=scText.nextLine();
        while (temp == listKH.size()) {
            for (int i = 0; i < listKH.size(); i++) {
                if (listKH.get(i).getMaUser().equalsIgnoreCase(maUser)) {
                    System.out.printf("Ma khach khach hang da duoc dung");
                    System.out.println("Yeu cau nhap lai");
                    System.out.printf("Nhap lai ma khach hang: ");
                    setMaUser(scText.nextLine());
                    continue;
                }
                temp++;
            }
        }
        temp=0;
        while (temp == listNV.size()) {
            for (int i = 0; i < listNV.size(); i++) {
                if (listNV.get(i).getMaNV().equalsIgnoreCase(maUser)) {
                    System.out.printf("Ma khach khach hang da duoc dung");
                    System.out.println("Yeu cau nhap lai");
                    System.out.printf("Nhap lai ma khach hang: ");
                    setMaUser(scText.nextLine());
                    continue;
                }
                temp++;
            }
        }
        super.nhap(listNV);


    }
    @Override
    public void xuat() {
        System.out.format("|%-10s",maUser);
        super.xuat();
    }

}
