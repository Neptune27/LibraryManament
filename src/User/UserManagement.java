package User;

import General.Common.ISaveLoad;
import General.Customer.Date;
import General.Input.NInteger;
import General.Input.NString;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;
import User.Errors.UsernameExistException;
import User.Errors.UsernameNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

//TODO Add 3 retry/Or not.
public class UserManagement implements ISaveLoad, IMenu {
    public ArrayList<StaffUser> users = new ArrayList<>();
    private static int latestUserId = 0;
    private static UserManagement instance;

    public static UserManagement getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserManagement();
        }
        return instance;
    }

    private UserManagement(){};



    public void addAdminDefault() {
        var admin = new AdminUser("admin", 18, ESex.NOT_SPECIFY, "0393406364", new Address("a", "a", "a", "a", "a"), getNewID(), "admin",
                "admin", 0, EWorkShift.BOTH, new Date("18-11-2022"));
        if (isUsernameExist(admin.getUsername())) {
            System.out.println("Ten tk da ton tai");
            return;
        }
        users.add(admin);
    }

    public void addAdminFromInput() {
        AdminUser newAdmin = new AdminUser();
        newAdmin.setFromInput();
        if (isUsernameExist(newAdmin.getUsername())) {
            System.out.println("Ten tk da ton tai");
            return;
        }
        users.add(newAdmin);
    }

    public void addThuKyFromInput() {
        ThuKyUser thuKy = new ThuKyUser();
        thuKy.setFromInput();
        if (isUsernameExist(thuKy.getUsername())) {
            System.out.println("Ten tk da ton tai");
            return;
        }
        users.add(thuKy);
    }

    public void addPhucVuFromInput() throws UsernameExistException {
        PhucVuUser phucVuUser = new PhucVuUser();
        phucVuUser.setFromInput();
        if (isUsernameExist(phucVuUser.getUsername())) {
            System.out.println("Ten tk da ton tai");
            return;
        }
        users.add(phucVuUser);
    }

    public void addMenuUserFromInput() throws RuntimeException{
        RunnableMenu menu = new RunnableMenu("Thêm tài khoản");
        menu.add("Thêm Phục Vụ",this::addPhucVuFromInput);
        menu.add("Thêm Thư Ký",this::addThuKyFromInput);
        menu.add("Thêm Quản Lý",this::addAdminFromInput);
        menu.show();
    }


    public static int getLatestUserId() {
        return latestUserId;
    }

    public static void setLatestUserId(int latestUserId) {
        UserManagement.latestUserId = latestUserId;
    }

    public StaffUser getUser(String username, String password) throws UsernameNotFoundException {
        var userList = this.users.stream().filter(user -> user.getUsername().equals(username) &&
                user.getPassword().equals(password)).toList();
        if (userList.size() == 0) {
            throw new UsernameNotFoundException("Username not found!");
        }
        return userList.get(0);
    }

    public boolean isUsernameExist(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).toList().size() > 0;
    }

    void register() {
        try {
            addMenuUserFromInput();
        } catch (RuntimeException e) {
            System.out.println("Tên tk đã tồn tại");
        }
    }

//    region CHANGE USER

    private void changeByFuncMenu(String menuName, Function<StaffUser, Boolean> function) {
        var validUsers = users.stream().filter(function::apply).toList();
        RunnableMenu menu = new RunnableMenu(menuName);
        menu.setRunOnce(true);
        for (var user: validUsers) {
            menu.add(user.getBasicInfo(), user::changePropertyMenu);
        }
        menu.show();
    }

    private void changeByAllMenu() {
        changeByFuncMenu("Tất cả", staffUser -> true);
    }

    private void changeByIDMenu() {
        int id = new NInteger("ID").getFromInput().getValue();
        changeByFuncMenu("ID", staffUser -> staffUser.getId() == id);
    }

    private void changeByNameMenu() {
        String name = new NString("tên").getFromInput().getValue();
        changeByFuncMenu("Tên", staffUser -> staffUser.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
    }

    private void changeByAgeMenu() {
        int age = new NInteger("tuổi").getFromInput().getValue();
        changeByFuncMenu("Tuổi", staffUser -> staffUser.getId() == age);
    }

    private void changeByUsernameMenu() {
        var username = new NString("username").getFromInput().getValue();
        changeByFuncMenu("Ssername", staffUser -> staffUser.getUsername().toUpperCase(Locale.ROOT).contains(username.toUpperCase(Locale.ROOT)));
    }

    public void changeUserMenu() {
        RunnableMenu menu = new RunnableMenu("Thay đổi");
        menu.add("Tất cả", this::changeByAllMenu);
        menu.add("Thay bằng ID", this::changeByIDMenu);
        menu.add("Thay bằng tên", this::changeByNameMenu);
        menu.add("Thay bằng tuổi", this::changeByAgeMenu);
        menu.add("Thay bằng tên tài khoản", this::changeByUsernameMenu);

        menu.show();

    }

//    endregion

//region SAVE LOAD
    public void save() {
        File file = new File("./src/Data/User.bin");
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file, false);
            out = new ObjectOutputStream(fos);
            out.writeObject(users);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static int getNewID() {
        latestUserId++;
        return latestUserId;
    }

    private void setNewLatestUserId() {
        int max = 0;
        for (var staffUser : users) {
            max = Math.max(max, staffUser.getId());
        }
        setLatestUserId(max);
    }

    public void load() {
        File file = new File("./src/Data/User.bin");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
            users = (ArrayList<StaffUser>) oos.readObject();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        finally {
            if (users.isEmpty()) {
                addAdminDefault();
            }
            setNewLatestUserId();
        }
    }
//endregion


    @Override
    public void menu() {
        RunnableMenu menu = new RunnableMenu("Quan Ly Tai Khoan");
        menu.addBackgroundTask(this::save);
        menu.add("Chỉnh tài khoản", this::changeUserMenu);
        menu.add("Thêm tài khoản", this::register);
        menu.show();
    }
}
