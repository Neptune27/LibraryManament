package General.Customer;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputInformation {
    //Check is num
    public boolean isNum(char c){
        if (c > '0' && c <'9')
            return true;
        return false;
    }
    //Delete space
    public String delSpace(String s){
        //delete space in the begin and the end of string
        s = s.trim();
        //delete space in the middle
        while (s.contains("  ")){
            s = s.replaceAll("  ", " ");
        }
        return s;
    }
    //Capitalize each word
    public String capEachWord(String s){
        String[] s_split = s.split(" ");
        String cap;
        s = "";
        for (String i:s_split){
            cap = i.substring(0,1).toUpperCase() + i.substring(1);
            s += cap + " ";
        }
        s = delSpace(s);
        return s;
    }
    //input name
    Scanner sc = new Scanner(System.in);
    public String inputName(){
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();

        //Check if it contains number
        while (true){
            boolean flag = true;
            for (int i = 0;i<name.length();i++) {
                if (isNum(name.charAt(i))) {
                    System.out.print("Tên không hợp lệ! Hãy nhập lại: ");
                    flag = false;
                }
            }
            if (!flag)
                name = sc.nextLine();
            else break;
        }
        //reformat name
        name = name.toLowerCase();
        name = delSpace(name);
        name = capEachWord(name);
        return name;
    }

    //input phone number
    public String inputPhoneNumber(){
        System.out.print("Nhập số điện thoại: ");
        String phone = sc.nextLine();

        while (true){
            if(!Pattern.matches("0\\d{9}",phone)){
                System.out.print("Số điện thoại không hợp lệ! Nhập lại: ");
                phone = sc.nextLine();
            }
            else break;
        }
        return phone;
    }
    //input email address
    public String inputEmail(){
        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        while (true){
            if(!Pattern.matches(".*\\w@gmail.com",email)){
                System.out.print("Email không hợp lệ! Nhập lại: ");
                email = sc.nextLine();
            }
            else break;
        }
        return email;
    }
    //input address
    public Address inputAddress(){
        Address address = new Address();
        String inp;
        System.out.println("Nhập địa chỉ!");
        System.out.print("Số nhà: ");
        inp = sc.nextLine();
        inp = delSpace(inp);
        address.setHouseNumber(inp);
        System.out.print("Tên đường: ");
        inp = sc.nextLine();
        inp = delSpace(inp);
        inp = capEachWord(inp);
        address.setStreet(inp);
        System.out.print("Phường: ");
        inp = sc.nextLine();
        inp = delSpace(inp);
        inp = capEachWord(inp);
        address.setWard(inp);
        System.out.print("Quận/ Huyện: ");
        inp = sc.nextLine();
        inp = delSpace(inp);
        inp = capEachWord(inp);
        address.setDistrict(inp);

        return address;
    }
    public String inputCustomerID(){
        String id = "";
        System.out.print("Nhập ID khách hàng(vd:HS001): ");
        return id;
    }
    //check leap year
    public boolean isLeapYear(int year){
        if (year % 4 == 0)
            return true;
        else return false;
    }
    //check date
    public boolean checkDate(int day,int month,int year){
        int MIN_YEAR = 2018;
        if (day <= 0 || day > 31) return false;
        if (month <=0 || month > 12) return false;
        if (year < MIN_YEAR) return false;

        int lst_30d_month[] = {4,6,9,11};
        for (int i : lst_30d_month)
            if (month == i)
                if (day > 30)
                    return false;
        if (month == 2) {
            if (isLeapYear(year)) {
                if (day > 29)
                    return false;
            } else {
                if (day > 28)
                    return false;
            }
        }
        return true;
    }
    //input date
    public Datetime inputDate(){
        Datetime datetime = new Datetime();
        String inp;
        System.out.print("Nhập ngày-tháng-năm(vd:01-01-2022): ");
        inp = sc.nextLine();
        //check format
        while (true){
            if (!Pattern.matches("\\d|\\d{2}-\\d|\\d{2}-\\d{4}",inp)){
                System.out.println("Ngày tháng không hợp lệ! Hãy nhập lại: ");
                inp = sc.nextLine();
            }
            else {
                String a_inp[] = inp.split("-");
                int day = Integer.parseInt(a_inp[0]);
                int month = Integer.parseInt(a_inp[1]);
                int year = Integer.parseInt(a_inp[2]);

                if (!checkDate(day,month,year)) {
                    System.out.println("Ngày tháng không hợp lệ! Hãy nhập lại: ");
                    inp = sc.nextLine();
                }else break;
            }
        }
        //check datetime right or wrong
        return datetime;
    }
}
