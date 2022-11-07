package Library;

import User.Errors.*;
import User.*;
import General.Input.*;
import General.Menu.RunnableMenu;

public class LoginManagement {

    static void login() {
        String username = new NString("Ten TK").getFromInput().getValue();
        String password = new NString("MK").getFromInput().getValue();

        try {
            var user = UserManagement.getUser(username, password);
            switch (user.getPermission()){
                case STAFF -> generateMenuStaff();
                case CUSTOMER -> generateMenuCustomer();
                case ADMIN -> generateMenuAdmin();
            }
        } catch (UsernameNotFoundException e) {
            System.out.println("Username not found!");
        }
    }

    static void register() {
        try {
            String username = new NString("Ten TK").getFromInput().getValue();
            String password = new NString("MK").getFromInput().getValue();
            String passwordRetype = new NString("lai MK").getFromInput().getValue();
            if (!password.equals(passwordRetype)) {
                register();
            }
            else {
                String name = new NString("ten").getFromInput().getValue();
                Integer birthYear = new NInteger("nam sinh").getFromInput().getValue();
                UserManagement.addUser(name, birthYear, username, password, EPermission.CUSTOMER);
            }
        }
        catch (UsernameExistException ex) {
            System.out.println("Tai khoan bi trung, vui long nhap ten tai khoan khac!");
            register();
        }

    }

    public static void main(String[] args) {
        generateMenu();
    }

    public static void generateMenuGuest() {

    }

    public static void generateMenuCustomer() {

    }

    public static void generateMenuStaff() {

    }

    public static void generateMenuAdmin() {

    }

    public static void save() {
        UserManagement.save();
    }

    public static void load() {
        UserManagement.load();
    }

    public static void generateMenu() {
        RunnableMenu menu = new RunnableMenu("Login Menu");
        menu.addSection("Login");
        menu.add("Login", ()->{});
        menu.add("Login as Guest", ()->{});
        menu.add("Register", LoginManagement::register);
        menu.add("Save", LoginManagement::save);
        menu.add("Load", LoginManagement::load);
        menu.addSection("Debug");
        menu.add("User Size", () -> System.out.println(UserManagement.users.size()));
        menu.add("Users: ", () -> System.out.println(UserManagement.users));
        menu.show();
    }
}
