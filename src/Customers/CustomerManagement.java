package Customers;


import Books.Book;
import Books.TicketManagement;
import General.Common.ISaveLoad;
import General.Input.NInteger;
import General.Input.NString;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class CustomerManagement implements ISaveLoad, IMenu {
    public ArrayList<Customer> customers = new ArrayList<>();
    private static int latestCustomerId = 0;
    private static CustomerManagement instance;

    public static CustomerManagement getInstance() {
        if (instance == null) {
            instance = new CustomerManagement();
        }
        return instance;
    }

    private CustomerManagement() {load();};


    public static void setLatestCustomerId(int latestCustomerId) {
        CustomerManagement.latestCustomerId = latestCustomerId;
    }

    public static int getNewID() {
        latestCustomerId++;
        return latestCustomerId;
    }

    // region SAVE LOAD
    @Override
    public void save() {
        File file = new File("./src/Data/Customer.bin");
        FileOutputStream fos;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file, false);
            out = new ObjectOutputStream(fos);
            out.writeObject(customers);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setNewLatestCustomerId() {
        int max = 0;
        for (var customer : customers) {
            max = Math.max(max, customer.getId());
        }
        setLatestCustomerId(max);
    }

    @Override
    public void load() {
        File file = new File("./src/Data/Customer.bin");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
            customers = (ArrayList<Customer>) oos.readObject();
            fis.close();
        } catch (ClassNotFoundException | IOException ignored) {
            System.out.println("CSDL cua Ticket khong co => Tao CSDL moi");
        }
        finally {
            setNewLatestCustomerId();
        }
    }
// endregion


    private void addCustomerMenu() {
        RunnableMenu menu = new RunnableMenu("Thêm TKKH");
        menu.setRunOnce(true);
        menu.add("Them khach hang thuong", ()->{
            Customer customer = new Customer();
            customer.setFromInput();
            customers.add(customer);
        });
        menu.add("Them khach hang thuong", ()->{
            Customer customer = new Customer();
            customer.setFromInput();
            customers.add(customer);
        });
        menu.add("Them khach hang cong nhan vien", ()->{
            Worker customer = new Worker();
            customer.setFromInput();
            customers.add(customer);
        });
        menu.add("Them khach hang SV/HS", ()->{
            Student customer = new Student();
            customer.setFromInput();
            customers.add(customer);
        });

        menu.show();
    }

    //    region CHANGE CUSTOMER

    private void changeByFuncMenu(String menuName, Function<Customer, Boolean> function) {
        var validUsers = customers.stream().filter(function::apply).toList();
        RunnableMenu menu = new RunnableMenu(menuName);
        menu.setRunOnce(true);
        for (var user: validUsers) {
            menu.add(user.getBasicInfo(), user::changePropertiesMenu);
        }
        menu.show();
    }

    private void changeByAllMenu() {
        changeByFuncMenu("Tất cả", customer -> true);
    }

    private void changeByIDMenu() {
        int id = new NInteger("ID").getFromInput().getValue();
        changeByFuncMenu("ID", customer -> customer.getId() == id);
    }

    private void changeByNameMenu() {
        String name = new NString("tên").getFromInput().getValue();
        changeByFuncMenu("Tên", customer -> customer.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
    }

    private void changeByAgeMenu() {
        int age = new NInteger("tuổi").getFromInput().getValue();
        changeByFuncMenu("Tuổi", customer -> customer.getId() == age);
    }


    public void changeCustomerMenu() {
        RunnableMenu menu = new RunnableMenu("Thay đổi");
        menu.add("Tất cả", this::changeByAllMenu);
        menu.add("Thay bằng ID", this::changeByIDMenu);
        menu.add("Thay bằng tên", this::changeByNameMenu);
        menu.add("Thay bằng tuổi", this::changeByAgeMenu);

        menu.show();

    }

//    endregion

    //    region GET CUSTOMER
    public Customer getCustomerMenu() {
        final Customer[] customerToGet = new Customer[1];
        RunnableMenu menu = new RunnableMenu("Khach Hang");
        menu.setRunOnce(true);
        for (var customer : customers) {
            menu.add(customer.getBasicInfo(), ()-> {
                customerToGet[0] = customer;
            });
        }
        menu.show();
        return customerToGet[0];
    }
//endregion

//    region REMOVE CUSTOMER

    private void removeByFuncMenu(String menuName, Function<Customer, Boolean> function) {
        var validUsers = customers.stream().filter(function::apply).toList();
        RunnableMenu menu = new RunnableMenu(menuName);
        menu.setRunOnce(true);
        for (var user: validUsers) {
            menu.add(user.getBasicInfo(), ()->{
                customers.remove(user);
            });
        }
        menu.show();
    }

    private void removeByAllMenu() {
        removeByFuncMenu("Tất cả", customer -> true);
    }

    private void removeByIDMenu() {
        int id = new NInteger("ID").getFromInput().getValue();
        removeByFuncMenu("ID", customer -> customer.getId() == id);
    }

    private void removeByNameMenu() {
        String name = new NString("tên").getFromInput().getValue();
        removeByFuncMenu("Tên", customer -> customer.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
    }

    private void removeByAgeMenu() {
        int age = new NInteger("tuổi").getFromInput().getValue();
        removeByFuncMenu("Tuổi", customer -> customer.getId() == age);
    }


    public void removeCustomerMenu() {
        RunnableMenu menu = new RunnableMenu("Xoa");
        menu.add("Tất cả", this::removeByAllMenu);
        menu.add("Thay bằng ID", this::removeByIDMenu);
        menu.add("Thay bằng tên", this::removeByNameMenu);
        menu.add("Thay bằng tuổi", this::removeByAgeMenu);

        menu.show();
    }
    public void findByNameMenu(){
        String name = new NString("tên").getFromInput().getValue();
        System.out.printf("|%-6s|%-25s|%-15s|%-11s|%-4s|%-26s\n","ID","Họ tên","Giới tính","SĐT","Tuổi","Địa chỉ");
        for (Customer cus:customers){
            if (cus.getName().contains(name)){
                System.out.printf("|%-6s|%-25s|%-15s|%-11s|%-4s|%-26s\n",cus.getId(),cus.getName(),cus.getSex(),cus.getPhoneNumber(),cus.getAge(),cus.getAddress());
            }
        }
    }
    public void findByIDMenu(){
        int id = new NInteger("id").getFromInput().getValue();
        System.out.printf("|%-6s|%-25s|%-15s|%-11s|%-4s|%-26s\n","ID","Họ tên","Giới tính","SĐT","Tuổi","Địa chỉ");
        for (Customer cus:customers){
            if (cus.getId() == id){
                System.out.printf("|%-6s|%-25s|%-15s|%-11s|%-4s|%-26s\n",cus.getId(),cus.getName(),cus.getSex(),cus.getPhoneNumber(),cus.getAge(),cus.getAddress());
            }
        }
    }
    public void findByAgeMenu(){
        int age = new NInteger("age").getFromInput().getValue();
        System.out.printf("|%-6s|%-25s|%-15s|%-11s|%-4s|%-26s\n","ID","Họ tên","Giới tính","SĐT","Tuổi","Địa chỉ");
        for (Customer cus:customers){
            if (cus.getAge() == age){
                System.out.printf("|%-6s|%-25s|%-15s|%-11s|%-4s|%-26s\n",cus.getId(),cus.getName(),cus.getSex(),cus.getPhoneNumber(),cus.getAge(),cus.getAddress());
            }
        }
    }
    public void findCustomerMenu(){
        RunnableMenu menu = new RunnableMenu("Tìm kiếm");
        menu.add("Theo tên",this::findByNameMenu);
        menu.add("Theo id",this::findByIDMenu);
        menu.add("Theo tuổi",this::findByAgeMenu);
        menu.show();
    }
//    endregion


    public ArrayList<Book> getBorrowedBook(int cusId) {
        return TicketManagement.getInstance().getBorrowedBookByCustomerID(cusId);
    }

    public String getBorrowedBookToString(int cusId) {
        var books = getBorrowedBook(cusId);
        StringBuilder val = new StringBuilder();
        for (var book : books) {
            val.append(book.getBookName()).append(", ");
        }
        if (val.length() == 0) {
            return "";
        }

        return val.substring(0, val.length()-2);
    }

    public void statistic() {
        System.out.println("========================================================================================" +
                "===========================================================================================");
        System.out.printf("| %-6s | %-31s | %-20s | %-11s | %-4s | %-34s | %-28s | %-30s |\n",
                "ID","Ho tên","Giới tính","SDT","Tuổi", "Sách mượn", "Dia chỉ", "Work/School");
        for (Customer cus : customers){
            System.out.printf("| %-6s | %-30s | %-17s | %-11s | %-4s | %-30s | %-26s | %-30s |\n",
                    cus.getId(),cus.getName(),cus.getSex(),cus.getPhoneNumber(),cus.getAge(),
                    getBorrowedBookToString(cus.getId()),cus.getAddress(), cus.getWork());
        }
        System.out.println("========================================================================================" +
                "===========================================================================================");
    }


    @Override
    public void menu() {
        RunnableMenu menu = new RunnableMenu("Quan Ly Khach Hang");
        menu.addBackgroundTask(this::save);
        menu.add("Thêm khách hàng", this::addCustomerMenu);
        menu.add("Chỉnh sửa khách hàng", this::changeCustomerMenu);
        menu.add("Xóa khách hàng", this::removeCustomerMenu);
        menu.add("Tìm khách hàng",this::findCustomerMenu);
        menu.add("Thống kê tong", this::statistic);
        menu.show();
    }


}
