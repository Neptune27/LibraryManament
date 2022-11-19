package Library;

import Books.BookManagement;
import General.Common.ISaveLoad;
import General.Menu.IMenu;
import User.Errors.*;
import User.*;
import General.Input.*;
import General.Menu.RunnableMenu;

public class LoginManagement implements ISaveLoad, IMenu {

    private final UserManagement userManagement = UserManagement.getInstance();
    private final BookManagement bookManagement = BookManagement.getInstance();


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



    public static void main(String[] args) {
        LoginManagement loginManagement = new LoginManagement();
        loginManagement.menu();
    }


    public static void generateMenuCustomer() {
    }


//    TODO Implement me.
    public void generateMenuPhucVu(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Phục Vụ");
        menu.addSection("Chức vụ phục vụ");
        menu.add("Tìm vị trí sách", bookManagement::findBookFromInput);
        menu.addSection("Quản lý tài khoản");
        menu.add("Chỉnh tên tài khoản", ()->{});
        menu.add("Chỉnh mật khẩu", ()->{});
        menu.add("Chỉnh tên", ()->{});
        menu.add("Xuất thông tin", ()->{});

        menu.show();

    }

    public void generateMenuThuKy(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Thư Ký");
        menu.addSection("Chức vụ thư ký");
        menu.add("Quản lý khách hàng", ()->{});
        menu.add("Quản lý sách", ()->{});
        menu.add("Quản lý mượn sách", ()->{});
        menu.addSection("Quản lý tài khoản");
        menu.add("Chỉnh tên tài khoản", ()->{});
        menu.add("Chỉnh mật khẩu", ()->{});
        menu.add("Chỉnh tên", ()->{});
        menu.add("Xuất thông tin", ()->{});

        menu.show();

    }

    public void generateMenuAdmin(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Admin");
        menu.add("Thống kê", ()->{});
        menu.add("Quản lý khách hàng", ()->{});
        menu.add("Quản lý sách", bookManagement::menu);
        menu.add("Quản lý thẻ mượn sách", bookManagement::menu);
        menu.add("Quản lý tài khoản", userManagement::menu);
        menu.add("Xuất thông tin", ()->{});

        menu.show();
    }



    public void save() {
        userManagement.save();
    }

    public void load() {
        userManagement.load();
    }

    public void menu() {
        RunnableMenu menu = new RunnableMenu("Login Menu");
        menu.addBackgroundTask(this::save);

        menu.addSection("Login");
        menu.add("Login", this::login);
//        menu.add("Change by name", userManagement::changeByName);
//        menu.add("Register", this::register);
//        menu.add("Save", this::save);
//        menu.add("Load", this::load);
//        menu.addSection("Debug");
//        menu.add("User Size", () -> System.out.println(userManagement.users.size()));
//        menu.add("Users: ", () -> System.out.println(userManagement.users));
//        menu.addSection("Debug - Ke sach");
//        menu.add("Vao QL Sach: ", bookManagement::menu);
        menu.show();
    }
}
