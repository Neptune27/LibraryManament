package Customers;


import General.Common.ISaveLoad;
import General.Input.NInteger;
import General.Input.NString;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;

import java.io.*;
import java.util.ArrayList;
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

    private CustomerManagement() {};


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
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
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

    @Override
    public void menu() {
        RunnableMenu menu = new RunnableMenu("Quan Ly Khach Hang");
        menu.addBackgroundTask(this::save);
        menu.add("Thêm khách hàng", this::addCustomerMenu);
        menu.add("Chỉnh sửa khách hàng", this::changeCustomerMenu);
//        menu.add("Xóa khách hàng");
//        menu.add("Thống kê");
    }
}
