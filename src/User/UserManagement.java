package User;

import Customers.Customer;
import General.Common.ISaveLoad;
import General.Common.Date;
import General.Input.NInteger;
import General.Input.NString;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;
import User.Errors.UsernameExistException;
import User.Errors.UsernameNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;


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

    private UserManagement(){}



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



    public void addMenuUserFromInput() throws RuntimeException {
        RunnableMenu menu = new RunnableMenu("Th??m t??i kho???n");
        menu.add("Th??m Ph???c V???", this::addPhucVuFromInput);
        menu.add("Th??m Th?? K??", this::addThuKyFromInput);
        menu.add("Th??m Qu???n L??", this::addAdminFromInput);
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

    public boolean isIDExist(int iD) {
        return users.stream().filter(user -> user.getId() == iD).toList().size() > 0;
    }

    void register() {
        try {
            addMenuUserFromInput();
        } catch (RuntimeException e) {
            System.out.println("T??n tk ???? t???n t???i");
        }
    }


//    region CHANGE USER

    private void changeByFuncMenu(String menuName, Function<StaffUser, Boolean> function) {
        var validUsers = users.stream().filter(function::apply).toList();
        RunnableMenu menu = new RunnableMenu(menuName);
        menu.setRunOnce(true);
        for (var user : validUsers) {
            menu.add(user.getBasicInfo(), user::changePropertyMenu);
        }
        menu.show();
    }

    private void changeByAllMenu() {
        changeByFuncMenu("T???t c???", staffUser -> true);
    }

    private void changeByIDMenu() {
        int id = new NInteger("ID").getFromInput().getValue();
        changeByFuncMenu("ID", staffUser -> staffUser.getId() == id);
    }

    private void changeByNameMenu() {
        String name = new NString("t??n").getFromInput().getValue();
        changeByFuncMenu("T??n", staffUser -> staffUser.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
    }

    private void changeByAgeMenu() {
        int age = new NInteger("tu???i").getFromInput().getValue();
        changeByFuncMenu("Tu???i", staffUser -> staffUser.getId() == age);
    }

    private void changeByUsernameMenu() {
        var username = new NString("username").getFromInput().getValue();
        changeByFuncMenu("Ssername", staffUser -> staffUser.getUsername().toUpperCase(Locale.ROOT).contains(username.toUpperCase(Locale.ROOT)));
    }

    public void changeUserMenu() throws FileNotFoundException {
        RunnableMenu menu = new RunnableMenu("Thay ?????i");
        menu.add("T???t c???", this::changeByAllMenu);
        menu.add("Thay b???ng ID", this::changeByIDMenu);
        menu.add("Thay b???ng t??n", this::changeByNameMenu);
        menu.add("Thay b???ng tu???i", this::changeByAgeMenu);
        menu.add("Thay b???ng t??n t??i kho???n", this::changeByUsernameMenu);
        menu.show();

    }

    public void statistic(List<StaffUser> users) {
        System.out.println("===================================================================================================================================================================================================================================");
        System.out.printf("|| %-5s || %-10s || %-20s || %-10s || %-3s || %-5s || %-11s || %-12s|| %-8s || %-10s || %-12s || %-4s || %-10s || %-50s ||\n",
                "ID", "Username", "Name", "Permission", "Age", "Shift", "Sex", "Day Leave", "Salary", "Bonus", "Total", "Rank", "Phone", "Address");
        for (var user : users) {
            System.out.printf("|| %-5s || %-10s || %-20s || %-10s || %-3s || %-5s || %-11s || %-12s|| %-8s || %-10s || %-12s || %-4s || %-10s || %-50s ||\n",
                    user.getId(), user.getUsername(), user.getName(), user.getPermission(), user.getAge(), user.getWorkShift(), user.getSex(), user.getDayLeave(),
                    user.getSalary(), user.getBonus(), user.getTotalSalary(), user.getRanking(), user.getPhoneNumber(), user.getAddress());
        }
        System.out.println("===================================================================================================================================================================================================================================");
    }


    private void statByIDMenu() {
        int id = new NInteger("ID").getFromInput().getValue();
        var validUsers = users.stream().filter(staffUser -> staffUser.getId() == id).toList();
        statistic(validUsers);
    }

    private void statByNameMenu() {
        String name = new NString("t??n").getFromInput().getValue();
        var validUsers = users.stream().filter( staffUser -> staffUser.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT))).toList();
        statistic(validUsers);
    }

    private void statByAgeMenu() {
        int age = new NInteger("tu???i").getFromInput().getValue();
        var validUsers = users.stream().filter(staffUser -> staffUser.getAge() == age).toList();
        statistic(validUsers);
    }

    private void statByUsernameMenu() {
        var username = new NString("username").getFromInput().getValue();
        var validUsers = users.stream().filter( staffUser -> staffUser.getUsername().toUpperCase(Locale.ROOT).contains(username.toUpperCase(Locale.ROOT))).toList();
        statistic(validUsers);
    }


    public void statisticMenu() throws FileNotFoundException {
        RunnableMenu menu = new RunnableMenu("Thay ?????i");
        menu.add("T???t c???", ()->statistic(users));
        menu.add("Tim b???ng ID", this::statByIDMenu);
        menu.add("Tim b???ng t??n", this::statByNameMenu);
        menu.add("Tim b???ng tu???i", this::statByAgeMenu);
        menu.add("Tim b???ng t??n t??i kho???n", this::statByUsernameMenu);
        menu.show();

    }




    private void deleteByAllMenu() {
        deleteByFuncMenu("T???t c???", staffUser -> true);
    }

    private void deleteByIDMenu() {
        int id = new NInteger("ID").getFromInput().getValue();
        deleteByFuncMenu("ID", staffUser -> staffUser.getId() == id);
    }

    private void deleteByNameMenu() {
        String name = new NString("T??n").getFromInput().getValue();
        deleteByFuncMenu("T??n", staffUser -> staffUser.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
    }

    private void deleteByAgeMenu() {
        int age = new NInteger("Tu???i").getFromInput().getValue();
        deleteByFuncMenu("Tu???i", staffUser -> staffUser.getId() == age);
    }

    private void deleteByUsernameMenu() {
        var username = new NString("username").getFromInput().getValue();
        deleteByFuncMenu("Ssername", staffUser -> staffUser.getUsername().toUpperCase(Locale.ROOT).contains(username.toUpperCase(Locale.ROOT)));
    }

    private void deleteByFuncMenu(String menuName, Function<StaffUser, Boolean> function) {
        var validUsers = users.stream().filter(function::apply).toList();
        RunnableMenu menu = new RunnableMenu(menuName);
        menu.setRunOnce(true);
        for (var user : validUsers) {
            menu.add(user.getBasicInfo(), ()->{
                users.remove(user);
            });
        }
        menu.show();
    }
    public void deleteUserMenu() {
        RunnableMenu menu = new RunnableMenu("Xo?? ");
        menu.add("T???t c???", this::deleteByAllMenu);
        menu.add("Xo?? b???ng ID", this::deleteByIDMenu);
        menu.add("Xo?? b???ng t??n", this::deleteByNameMenu);
        menu.add("Xo?? b???ng tu???i", this::deleteByAgeMenu);
        menu.add("Xo?? b???ng t??n t??i kho???n", this::deleteByUsernameMenu);
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
        } catch (ClassNotFoundException | IOException ignored) {
            System.out.println("CSDL cua User khong co => Tao CSDL moi");
        } finally {
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
        menu.add("Ch???nh t??i kho???n", this::changeUserMenu);
        menu.add("Th??m t??i kho???n", this::register);
        menu.add("Xo?? t??i kho???n", this::deleteUserMenu);
        menu.add("Thong ke", this::statisticMenu);
        menu.show();
    }
}
