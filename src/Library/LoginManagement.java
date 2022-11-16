package Library;

import User.Errors.*;
import User.*;
import General.Input.*;
import General.Menu.RunnableMenu;

public class LoginManagement {

    private final UserManagement userManagement = new UserManagement();

    public LoginManagement() {
        load();
    }

    void login() {
        String username = new NString("Ten TK").getFromInput().getValue();
        String password = new NString("MK").getFromInput().getValue();

        try {
            var user = userManagement.getUser(username, password);
            switch (user.getPermission()){
                case PHUC_VU -> generateMenuPhucVu(user);
                case THU_KY -> generateMenuThuKy(user);
                case CUSTOMER -> generateMenuCustomer();
                case ADMIN -> generateMenuAdmin(user);
            }
        } catch (UsernameNotFoundException e) {
            System.out.println("Username not found!");
        }
    }

    void register() {
        try {
            userManagement.addMenuUserFromInput();
        } catch (RuntimeException e) {
            System.out.println("Tên tk đã tồn tại");
        }

    }

    public static void main(String[] args) {
        LoginManagement loginManagement = new LoginManagement();
        loginManagement.generateMenu();
    }


    public static void generateMenuCustomer() {
    }


//    TODO Implement me.
    public static void generateMenuPhucVu(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Phục Vụ");
        menu.add("Tìm vị trí sách", ()->{});
        menu.addSection("Quản lý tài khoản");
        menu.add("Chỉnh tên tài khoản", ()->{});
        menu.add("Chỉnh mật khẩu", ()->{});
        menu.add("Chỉnh tên", ()->{});
        menu.add("Xuất thông tin", ()->{});
    }

    public static void generateMenuThuKy(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Thư Ký");
        menu.add("Tìm vị trí sách", ()->{});
        menu.add("Thống kê", ()->{});
        menu.add("Quản lý khách hàng", ()->{});
        menu.add("Quản lý sách", ()->{});
        menu.addSection("Quản lý tài khoản");
        menu.add("Chỉnh tên tài khoản", ()->{});
        menu.add("Chỉnh mật khẩu", ()->{});
        menu.add("Chỉnh tên", ()->{});
        menu.add("Xuất thông tin", ()->{});
    }

    public static void generateMenuAdmin(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Thư Ký");
        menu.add("Thống kê", ()->{});
        menu.add("Quản lý khách hàng", ()->{});
        menu.add("Quản lý sách", ()->{});
        menu.add("Quản lý tài khoản", ()->{});
        menu.add("Xuất thông tin", ()->{});
    }



    public void save() {
        userManagement.save();
    }

    public void load() {
        userManagement.load();
    }

    public void generateMenu() {
        RunnableMenu menu = new RunnableMenu("Login Menu");
        menu.addSection("Login");
        menu.add("Login", ()->{});
        menu.add("Login as Guest", ()->{});
        menu.add("Change by name", userManagement::changeByName);
        menu.add("Register", this::register);
        menu.add("Save", this::save);
        menu.add("Load", this::load);
        menu.addSection("Debug");
        menu.add("User Size", () -> System.out.println(userManagement.users.size()));
        menu.add("Users: ", () -> System.out.println(userManagement.users));
        menu.show();
    }
}
