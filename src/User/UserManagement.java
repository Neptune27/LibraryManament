package User;

import General.Common.ISaveLoad;
import General.Input.NString;
import General.Menu.RunnableMenu;
import User.Errors.UsernameExistException;
import User.Errors.UsernameNotFoundException;

import java.io.*;
import java.util.ArrayList;

//TODO Add 3 retry/Or not.
public class UserManagement implements ISaveLoad {
    public ArrayList<StaffUser> users = new ArrayList<>();
    private static int latestUserId = 0;

    public void addUser(String name, int age, String username, String password, EPermission permission) throws UsernameExistException {
        if (isUsernameExist(username)) {
            throw new UsernameExistException("Username da ton tai!");
        }
        switch (permission) {
            case CUSTOMER -> {}
            case THU_KY -> {
            }
            case PHUC_VU -> {

            }
            case ADMIN -> {

            }
        };
    }

    public void addAdminDefault() {
        AdminUser newAdmin = new AdminUser();
    }

    public void addAdminFromInput() throws UsernameExistException {
        AdminUser newAdmin = new AdminUser();
        newAdmin.setFromInput();
        if (isUsernameExist(newAdmin.getUsername())) {
            throw new UsernameExistException("Username da ton tai");
        }
        users.add(newAdmin);
    }

    public void addThuKyFromInput() throws UsernameExistException {
        ThuKyUser newAdmin = new ThuKyUser();
        newAdmin.setFromInput();
        if (isUsernameExist(newAdmin.getUsername())) {
            throw new UsernameExistException("Username da ton tai");
        }
        users.add(newAdmin);
    }

    public void addPhucVuFromInput() throws UsernameExistException {
        PhucVuUser newAdmin = new PhucVuUser();
        newAdmin.setFromInput();
        if (isUsernameExist(newAdmin.getUsername())) {
            throw new UsernameExistException("Username da ton tai");
        }
        users.add(newAdmin);
    }

    public void addMenuUserFromInput() throws RuntimeException{
        RunnableMenu menu = new RunnableMenu("Thêm tài khoản");
        menu.add("Thêm Phục Vụ",this::addPhucVuFromInput);
        menu.add("Thêm Thư Ký",this::addThuKyFromInput);
        menu.add("Thêm Quản Lý",this::addAdminFromInput);
        menu.show();
    }

    public static int getNewID() {
        latestUserId++;
        return latestUserId;
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

    public void changeByName() {
        String name = new NString("tên").getFromInput().getValue();
        var validUsers = users.stream().filter(staffUser -> staffUser.getName().contains(name)).toList();
        RunnableMenu menu = new RunnableMenu();
        menu.setRunOnce(true);
        for (var user: validUsers) {
            menu.add(user.getName(), user::changePropertyMenu);
        }
        menu.show();
    }

    public void changeUserFromInputMenu() {
        RunnableMenu menu = new RunnableMenu("Thay đổi");
        menu.add("Thay bằng tên", this::changeByName);

        menu.show();

    }

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
            setNewLatestUserId();
        }
    }
}
