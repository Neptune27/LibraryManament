package Library;

import Books.BookManagement;
import Books.TicketManagement;
import Customers.CustomerManagement;
import General.Common.ISaveLoad;
import General.Menu.IMenu;
import User.Errors.*;
import User.*;
import General.Input.*;
import General.Menu.RunnableMenu;

public class LoginManagement implements ISaveLoad, IMenu {

    private final UserManagement userManagement = UserManagement.getInstance();
    private final BookManagement bookManagement = BookManagement.getInstance();
    private final CustomerManagement customerManagement = CustomerManagement.getInstance();
    private final TicketManagement ticketManagement = TicketManagement.getInstance();


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


//    TODO Implement me.
    public void generateMenuPhucVu(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Phục Vụ");
        menu.addSection("Chức vụ phục vụ");
        menu.add("Tìm vị trí sách", bookManagement::findBookFromInput);
        menu.addSection("Quản lý tài khoản");
        menu.add("Chỉnh tài khoản", user::changePropertyMenu);

        menu.show();

    }

    public void generateMenuThuKy(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Thư Ký");
        menu.addSection("Chức vụ thư ký");
        menu.add("Quản lý khách hàng", customerManagement::menu);
        menu.add("Quản lý sách", bookManagement::menu);
        menu.add("Quản lý mượn sách", ticketManagement::menu);
        menu.addSection("Quản lý tài khoản");
        menu.add("Chỉnh tài khoản", user::changePropertyMenu);
        menu.show();

    }

    public void generateMenuAdmin(StaffUser user) {
        RunnableMenu menu = new RunnableMenu("Admin");
        menu.add("Quản lý khách hàng", customerManagement::menu);
        menu.add("Quản lý sách", bookManagement::menu);
        menu.add("Quản lý thẻ mượn sách", ()->ticketManagement.menu(user));
        menu.add("Quản lý tài khoản", userManagement::menu);
        menu.show();
    }



    public void save() {
        userManagement.save();
    }

    public void load() {
        userManagement.load();
    }

    public void menu() {
        ticketManagement.setBookManagement(bookManagement);
        ticketManagement.setCustomerManagement(customerManagement);

        bookManagement.setTicketManagement(ticketManagement);

        RunnableMenu menu = new RunnableMenu("Login Menu");
        menu.addBackgroundTask(this::save);

        menu.addSection("Login");
        menu.add("Login", this::login);
        menu.show();
        test
    }
}
