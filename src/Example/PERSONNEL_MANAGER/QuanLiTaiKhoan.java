package Example.PERSONNEL_MANAGER;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLiTaiKhoan implements Serializable, LuuDocFile {
    private String saveMaNV;
    private ArrayList<Account> listAccounts = new ArrayList<>();
    Scanner scInt = new Scanner(System.in);
    Scanner scText = new Scanner(System.in);

    static void bangChucVu() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("|                       CHON CHUC VU NHAN VIEN                     |");
        System.out.println("--------------------------------------------------------------------");
        System.out.format("|%-22s%-22s%-22s|\n", "1. Quan li", "2. Thu ki", "3. Phuc vu");
        System.out.println("--------------------------------------------------------------------");
        ;
    }

    public QuanLiTaiKhoan() {

    }

    public QuanLiTaiKhoan(ArrayList<Account> listAccounts) {
        this.listAccounts = listAccounts;

    }

    public String getSaveMaNV() {
        return saveMaNV;
    }

    public void setSaveMaNV(String saveMaNV) {
        this.saveMaNV = saveMaNV;
    }

    public ArrayList<Account> getListAccounts() {
        return listAccounts;
    }


    public void setListAccounts(ArrayList<Account> listAccounts) {
        this.listAccounts = listAccounts;
    }

    @Override
    public void Read() {
        File rfile = new File(".\\src\\Example\\PERSONNEL_MANAGER\\FILE\\TKhoan.bin");
        try {
            InputStream is = new FileInputStream(rfile);
            ObjectInputStream ois = new ObjectInputStream(is);
            Account account = (Account) ois.readObject();
            while (account != null) {
                this.listAccounts.add(account);
                account = (Account) ois.readObject();
            }
        } catch (Exception e) {
            System.out.println("Thanh cong");
        }
    }

    @Override
    public void Save() {
        File wfile = new File(".\\src\\Example\\PERSONNEL_MANAGER\\FILE\\TKhoan.bin");
        try {
            OutputStream os = new FileOutputStream(wfile);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (Account account : listAccounts) {
                oos.writeObject(account);
            }
            oos.flush();
            oos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean TimKiemTK(String userName) {
        if (listAccounts.isEmpty())
            return false;
        for (int i = 0; i < listAccounts.size(); i++) {
            if (userName.equalsIgnoreCase(listAccounts.get(i).getAccount()))
                return true;
        }
        return false;
    }

    public boolean ThemTK(Account acc) {
        if (!TimKiemTK(acc.getAccount()) || listAccounts.isEmpty()) {
            listAccounts.add(acc);
            return true;
        }
        return false;
    }

    public boolean XoaTK(Account acc) {
        if (TimKiemTK(acc.getAccount())) {
            listAccounts.remove(acc);
            return true;
        }
        return false;
    }

    public boolean ThaydoiMK(String userName) {
        for (int i = 0; i < listAccounts.size(); i++) {
            if (TimKiemTK(userName)) {
                System.out.printf("Nhap mat khau moi: ");
                listAccounts.get(i).setPassword(scText.nextLine());
                return true;
            }
        }
        return false;
    }

    public boolean dangnhap(ArrayList<Account> list, String maKey) {
        int nhapsai=0;
        String tenDangnhap;
        String matKhau;
        if (list.isEmpty())
            return false;
//        int temp=0;
        int i=0;
        boolean test = false;
        System.out.printf("Nhap tai khoan nguoi dung: ");
        tenDangnhap = scText.nextLine();
        while (!test) {
            for (; i < list.size(); i++) {
                test = tenDangnhap.equalsIgnoreCase(list.get(i).getAccount()) && maKey.equalsIgnoreCase(list.get(i).getKey());
                if (test) {
                    break;
                }
            }
            if (test){
                break;
            }
            else {
                nhapsai++;
                System.out.println("Sai ten dang nhap hoac ten dang nhap chua ton tai");
                System.out.printf("Nhap lai ten dang nhap: ");
                setSaveMaNV(tenDangnhap = scText.nextLine());
                if (nhapsai==3){
                    System.out.println("Nhap sai qua nhieu lan...");
                    nhapsai=0;
                    return false;
                }
                i=0;
                continue;
            }
        }
        i=0;
        test = false;
        System.out.printf("Nhap mat khau: ");
        matKhau=scText.nextLine();
        while (!test) {
            for (; i < list.size(); i++) {
                test =(maKey.equalsIgnoreCase(list.get(i).getKey()) && list.get(i).getAccount().equalsIgnoreCase(tenDangnhap)
                        && list.get(i).getPassword().equalsIgnoreCase(matKhau));
                if (test) {
                    setSaveMaNV(list.get(i).getMaNV());
                    break;
                }
            }
            if (test)
                break;
            else {
                nhapsai++;
                System.out.println("Sai mat khau");
                System.out.printf("Nhap lai mat khau dang nhap: ");
                matKhau = scText.nextLine();
                if (nhapsai==3){
                    System.out.println("Nhap sai qua nhieu lan...");
                    nhapsai=0;
                    return false;
                }
                i=0;
                continue;
            }
        }
        return true;
    }


}
