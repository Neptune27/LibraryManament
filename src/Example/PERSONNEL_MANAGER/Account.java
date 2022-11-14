package Example.PERSONNEL_MANAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Account implements Serializable {
    static String[] mangKey = {"Phuc vu", "Thu ki", "Quan li", "Khach hang"};
    private String key;
    private String maNV;
    private String account;
    protected String password;
    Scanner scText = new Scanner(System.in);

    public Account() {
    }

    public Account(String maNV, String key, String account, String password) {
        this.account = account;
        this.password = password;
        this.maNV = maNV;
        this.key = key;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public boolean taoTaiKhoan(ArrayList<Account> list, ArrayList<Account> list1, String maNV, String key) {
        this.maNV = maNV;
        this.key = key;
        int i;
        System.out.printf("Nhap ten dang nhap: ");
        account = scText.nextLine();
        if (list1.isEmpty()){
            System.out.printf("Nhap mat khau nguoi dung: ");
            password = scText.nextLine();
            if (password==null)
                return false;
            return true;
        }
        if (list.isEmpty()){
            System.out.printf("Nhap mat khau nguoi dung: ");
            password = scText.nextLine();
            if (password==null)
                return false;
            return true;
        }

        boolean test = false;
        while (!test) {
            i=0;
            for (; i < list.size(); i++) {
                test = list.get(i).getAccount().equalsIgnoreCase(account) || account.equalsIgnoreCase("admin");
                if (test) {
                    System.out.println("Ten dang nhap da duoc dung");
                    System.out.println("Yeu cau nhap lai");
                    System.out.printf("Nhap lai ten dang nhap: ");
                    setAccount(scText.nextLine());
                    i = -1;
                    continue;
                } else
                    break;
            }
            if (test){
                break;
            }

        }
        test=false;
        i=0;
        while (!test) {
            for (; i < list1.size(); i++) {
                test = list1.get(i).getAccount().equalsIgnoreCase(account) || account.equalsIgnoreCase("admin");
                if (test) {
                    System.out.println("Ten dang nhap da duoc dung");
                    System.out.println("Yeu cau nhap lai");
                    System.out.printf("Nhap lai ten dang nhap: ");
                    setAccount(scText.nextLine());
                    i = -1;
                    continue;
                }
                else break;
            }
            if (test) {
                break;
            }
        }
        System.out.printf("Nhap mat khau nguoi dung: ");
        password = scText.nextLine();
        if (password==null)
            return false;
        return true;
    }

    public void xuat() {
        System.out.format("|%-10s|%-10s|%-15s|%-15s|\n", key, maNV, account, password);
    }
}
