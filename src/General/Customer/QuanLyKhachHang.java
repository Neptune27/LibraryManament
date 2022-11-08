package General.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyKhachHang{
    private transient Scanner sc = new Scanner(System.in);
    private static ArrayList<Customer> listCus = new ArrayList<Customer>();

    public static void menu(){

    }

    public void addCus(){
        System.out.print("Nhập số lượng cần thêm: ");
        int n = Integer.parseInt(sc.nextLine());
        Customer cus = null;
        for (int i = 0;i<n;i++) {
            System.out.println("1. Thêm học sinh/sinh viên");
            System.out.println("2. Thêm công nhân viên: ");
            int option;
            System.out.print("Lựa chọn: ");
            option = Integer.parseInt(sc.nextLine());
            switch (option){
                case 1:
                    cus = new Student();
                    break;
                case 2:
                    cus = new Worker();
            }
            System.out.print("ID khách hàng: ");
            cus.setId(sc.nextLine());
            System.out.print("Tên khách hàng: ");
            cus.setName(sc.nextLine());
            System.out.print("Số điện thoại: ");
            cus.setPhone(sc.nextLine());
            System.out.print("Địa chỉ: ");
            InputInformation inp = new InputInformation();
            cus.setAddress(inp.inputAddress());
            System.out.print("Nhập ngày sinh!\n");
            cus.setBirthday(inp.inputDate());

            if (cus instanceof Student) {
                System.out.print("Nhập trường: ");
                ((Student) cus).setSchool(sc.nextLine());
            }else if(cus instanceof Worker){
                System.out.print("Nhập nơi làm việc: ");
                ((Worker) cus).setWorkPlace(sc.nextLine());
            }
            listCus.add(cus);
        }
        Customer.setAmount(Student.getAmount() + Worker.getWorkerAmount());
    }
    public static void writeDB(){
        File file = new File("./src/General/Customer/Data/Customers.bin");
        try{
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
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
}