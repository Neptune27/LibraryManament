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

//    WTF
    public void taoTaiKhoan(ArrayList<Account> list, ArrayList<Account> list1, String maNV, String key) {
        this.maNV = maNV;
        this.key = key;
        int temp = 0;
        System.out.printf("Nhap ten dang nhap: ");
        account = scText.nextLine();
        boolean testAD=account.equalsIgnoreCase("admin");
        while (true) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAccount().equalsIgnoreCase(account) || testAD) {
                    System.out.println("Ten dang nhap da duoc dung");
                    System.out.println("Yeu cau nhap lai");
                    System.out.printf("Nhap lai ten dang nhap: ");
                    setAccount(scText.nextLine());
                    i = -1;
                    temp=0;
                    continue;
                }
                temp++;
            }
            if (temp == list.size())
                break;
        }
        temp=0;
        while (true) {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).getAccount().equalsIgnoreCase(account) || testAD) {
                    System.out.println("Ten dang nhap da duoc dung");
                    System.out.println("Yeu cau nhap lai");
                    System.out.printf("Nhap lai ten dang nhap: ");
                    setAccount(scText.nextLine());
                    i = -1;
                    temp=0;
                    continue;
                }
                temp++;
            }
            if (temp == list1.size())
                break;
        }

        System.out.printf("Nhap mat khau nguoi dung: ");
        password = scText.nextLine();
    }

    public void xuat() {
        System.out.format("|%-10s|%-10s|%-15s|%-15s|\n", key, maNV, account, password);
    }
}
