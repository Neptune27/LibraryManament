package General.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class QuanLyKhachHang{
    private transient Scanner sc = new Scanner(System.in);
    private static ArrayList<Customer> listCus = new ArrayList<Customer>();

    public static void menu(){
        System.out.println("*****************************************");
        System.out.println("*          Quản lí khách hàng           *");
        System.out.println("*1. In thông tin toàn bộ khách hàng     *");
        System.out.println("*2. Tìm khách hàng theo ID              *");
        System.out.println("*3. Tìm khách hàng theo tên             *");
        System.out.println("*4. Thêm khách hàng                     *");
        System.out.println("*5. Xóa khách hàng                      *");
        System.out.println("*6. Chỉnh sửa thông tin khách           *");
        System.out.println("*7. Thoát                               *");
        System.out.println("*            Quản lí CSDL               *");
        System.out.println("*8. Đọc CSDL                            *");
        System.out.println("*9. Ghi mới vào CSDL                    *");
        System.out.println("*****************************************");

    }
    private static void printAttribute(){
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-6s%-30s%-40s%-12s%-12s%-20s\n","ID","Họ tên","Địa chỉ","SĐT","Ngày sinh","Nơi học/làm");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    }
    private static void printOneCus(Customer cus){
        String place ="";
        if (cus instanceof Student){
            place = ((Student) cus).getSchool();
        }else if(cus instanceof Worker)
            place = ((Worker) cus).getWorkPlace();
        System.out.printf("%-6s%-30s%-40s%-12s%-12s%-20s\n",cus.getId(),cus.getName(),cus.getAddress(),cus.getPhone(),cus.getBirthday(),place);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    }
    //In ra toan bo khach hang trong array list
    public static void printAllInfo(){
        printAttribute();
        for (Customer cus:listCus){
            printOneCus(cus);
        }
    }
    //Tim khach hang theo ID
    public static void findById(){
        Scanner sc = new Scanner(System.in);
        String option = "";
        while (true){
            System.out.print("Nhập ID khách hàng cần tìm: ");
            String inp = sc.nextLine();
            inp = inp.toUpperCase();
            for (Customer cus : listCus){
                if (inp.equals(cus.getId())){
                    printAttribute();
                    printOneCus(cus);
                    return;
                }
            }
            System.out.println("ID không tồn tại, nhập p để thoát hoặc ENTER(hoặc bất kì) để tìm lại: ");
            option = sc.nextLine();
            if (option.equals("p")) return;
        }
    }
    //tim khach hang theo ten
    public static void findByName(){
        Scanner sc = new Scanner(System.in);
        String option = "";
        while (true){
            System.out.print("Nhập tên khách hàng cần tìm: ");
            boolean flag = false;
            String inp = sc.nextLine();
            printAttribute();
            for (Customer cus : listCus){
                if (cus.getName().contains(inp)){
                    printOneCus(cus);
                    flag = true;
                }
            }
            if (flag) return;
            else {
                System.out.println("Tên không tồn tại, nhập p để thoát hoặc ENTER(hoặc bất kì) để tìm lại: ");
                option = sc.nextLine();
                if (option.equals("p")) return;
            }
        }
    }
    //kiem tra ton tai ID
    private static boolean checkID(String id){
        for (Customer cus : listCus){
            if (cus.getId().equals(id)) return true;
        }
        return false;
    }
    //them khach hang vao array list
    public static void addCus(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số lượng cần thêm: ");
        int n = Integer.parseInt(sc.nextLine());
        Customer cus = null;
        int option;
        for (int i = 0;i<n;i++) {
            while (true) {
                System.out.println("__________________________");
                System.out.printf("Thêm khách hàng thứ %d\n", (i + 1));
                System.out.println("1. Thêm học sinh/sinh viên");
                System.out.println("2. Thêm công nhân viên");
                System.out.println("__________________________");

                System.out.print("Lựa chọn: ");
                option = Integer.parseInt(sc.nextLine());
                if (option != 1 && option != 2){
                    System.out.println("Chỉ được nhập 1 hoặc 2!");
                }else break;
            }
            switch (option){
                case 1:
                    cus = new Student();
                    break;
                case 2:
                    cus = new Worker();
                    break;
            }
            InputInformation inp = new InputInformation();
            while (true) {
                cus.setId(inp.inputCustomerID());
                if (checkID(cus.getId())) System.out.println("ID đã tồn tại! Nhập lại!");
                else {
                    if (cus instanceof Student) {
                        if (cus.getId().contains("ST")) break;
                        else System.out.println("ID của học sinh phải có ST ở đầu");
                    }else if(cus instanceof Worker){
                        if (cus.getId().contains("WR")) break;
                        else System.out.println("ID của công nhân viên phải có WR ở đầu");
                    }
                }
            }
            cus.setName(inp.inputName("Tên khách hàng"));
            cus.setPhone(inp.inputPhoneNumber());
            cus.setAddress(inp.inputAddress());
            System.out.print("Nhập ngày sinh!\n");
            cus.setBirthday(inp.inputDate());
            if (cus instanceof Student) {
                System.out.print("Nhập trường: ");
                ((Student) cus).setSchool(inp.delSpace(sc.nextLine()));
            }else if(cus instanceof Worker){
                System.out.print("Nhập nơi làm việc: ");
                ((Worker) cus).setWorkPlace(inp.delSpace(sc.nextLine()));
            }
            listCus.add(cus);
        }
        Customer.setAmount(Student.getAmount() + Worker.getWorkerAmount());
    }
    //Xóa khách hàng
    public static void delCus(){
        Scanner sc = new Scanner(System.in);
        String option = "";
        boolean flag = false;
        Customer tmp = null;
        while (true){
            System.out.print("Nhập ID khách hàng cần xóa: ");
            String inp = sc.nextLine();
            inp = inp.toUpperCase();
            for (Customer cus : listCus){
                if (inp.equals(cus.getId())){
                    tmp = cus;
                    flag = true;
                    break;
                }
            }
            if (flag){
                printAttribute();
                printOneCus(tmp);
                System.out.println("Xóa khách hàng mã " + tmp.getId() + "?");
                while (true){
                    System.out.println("||============||");
                    System.out.println("||1. Đồng ý   ||");
                    System.out.println("||2. Đổi      ||");
                    System.out.println("||3. Thoát    ||");
                    System.out.println("||============||");
                    System.out.print("Lựa chọn: ");
                    option = sc.nextLine();
                    if (!option.equals("1") && !option.equals("2") && !option.equals("3"))
                        System.out.println("Chỉ được nhập 1,2,3!");
                    else {
                        switch (option){
                            case "1":
                                listCus.remove(tmp);
                                System.out.println("Đã xóa thành công");
                                return;
                            case "2":
                                System.out.println("Đổi khách cần xóa!");
                                return;
                            case "3":
                                return;
                        }
                    }
                }
            }else {
                System.out.print("ID không tồn tại! Nhập p để thoát hoặc bất kì để tiếp tục: ");
                option = sc.nextLine();
                if (option.equals("p")) break;
            }
        }
    }
    //chỉnh sửa thông tin khách hàng
    public static void editInfo() {
        Scanner sc = new Scanner(System.in);
        String option = "";
        boolean flag = false;
        Customer tmp = null;
        while (true) {
            System.out.print("Nhập ID khách hàng cần chỉnh sửa: ");
            String inp = sc.nextLine();
            inp = inp.toUpperCase();
            for (Customer cus : listCus) {
                if (inp.equals(cus.getId())) {
                    tmp = cus;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                printAttribute();
                printOneCus(tmp);
                System.out.println("Chỉnh sửa khách hàng mã " + tmp.getId() + "?");
                while (true) {
                    System.out.println("||==========||");
                    System.out.println("||1. Đồng ý ||");
                    System.out.println("||2. Đổi    ||");
                    System.out.println("||3. Thoát  ||");
                    System.out.println("||==========||");
                    System.out.print("Lựa chọn: ");
                    option = sc.nextLine();
                    if (!option.equals("1") && !option.equals("2") && !option.equals("3"))
                        System.out.println("Chỉ được nhập 1,2,3!");
                    else break;
                }
                switch (option) {
                    case "1":
                        while (true) {
                            System.out.println("||=====================||");
                            System.out.println("1. Sửa tên");
                            System.out.println("2. Sửa địa chỉ");
                            System.out.println("3. Sửa số điện thoại");
                            System.out.println("4. Sửa ngày sinh");
                            System.out.println("5. Sửa nơi học/làm việc");
                            System.out.println("6. Thoát");
                            System.out.println("||=====================||");
                            option = sc.nextLine();
                            if (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5") && !option.equals("6"))
                                System.out.println("Chỉ được nhập 1-6!");
                            InputInformation inpData = new InputInformation();
                            switch (option) {
                                case "1":
                                    tmp.setName(inpData.inputName("Nhập tên mới"));
                                    break;
                                case "2":
                                    System.out.println("Nhập địa chỉ mới!");
                                    tmp.setAddress(inpData.inputAddress());
                                    break;
                                case "3":
                                    System.out.print("Nhập số điện thoại mới: ");
                                    tmp.setPhone(inpData.inputPhoneNumber());
                                    break;
                                case "4":
                                    System.out.println("Nhập ngày sinh mới!");
                                    tmp.setBirthday(inpData.inputDate());
                                    break;
                                case "5":
                                    System.out.print("Nhập nơi học/làm việc mới: ");
                                    if (tmp instanceof Student)
                                        ((Student) tmp).setSchool(inpData.delSpace(sc.nextLine()));
                                    else if (tmp instanceof Worker)
                                        ((Worker) tmp).setWorkPlace(inpData.delSpace(sc.nextLine()));
                                    break;
                                case "6":
                                    return;
                            }
                        }

                    case "2":
                        System.out.println("Đổi khách cần chỉnh sửa!");
                        break;
                    case "3":
                        return;
                }
            } else {
                System.out.print("ID không tồn tại! Nhập p để thoát hoặc bất kì để tiếp tục: ");
                option = sc.nextLine();
                if (option.equals("p")) break;
            }
        }
    }
    public static void writeDB(){
        File file = new File("./src/General/Customer/Data/Customers.bin");
        try{
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(listCus);
            for (Customer cus: listCus){
                oos.writeObject(cus);
            }
            oos.flush();
            oos.close();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void load() {
        File file = new File("./src/General/Customer/Data/Customers.bin");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
            listCus = (ArrayList<Customer>) oos.readObject();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void doAllTask(){
        Scanner sc = new Scanner(System.in);
        String option;
        String ops[] = {"1","2","3","4","5","6","7","8","9"};
        boolean flag = false;
        while (true){
            menu();
            System.out.print("Nhập lựa chọn 1-9: ");
            option = sc.nextLine();
            for (String s : ops){
                if (s.equals(option)){
                    flag = true;
                    break;
                }
            }
            if (!flag){
                System.out.println("Bạn phải nhập trong khoảng 1-9");
            }else {
                switch (option){
                    case "1":
                        printAllInfo();
                        break;
                    case "2":
                        findById();
                        break;
                    case "3":
                        findByName();
                        break;
                    case "4":
                        addCus();
                        break;
                    case "5":
                        delCus();
                        break;
                    case "6":
                        editInfo();
                        break;
                    case "7":
                        return;
                    case "8":
                        load();
                        System.out.println("Đã load dữ liệu!");
                        break;
                    case "9":
                        writeDB();
                        System.out.println("Hoàn thành!");
                        break;
                }
            }
        }
    }
}