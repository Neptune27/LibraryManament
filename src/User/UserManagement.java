package User;

import Book.Customer;
import User.Errors.UsernameExistException;
import User.Errors.UsernameNotFoundException;

import java.io.*;
import java.util.ArrayList;

public class UserManagement {
    public static ArrayList<User> users = new ArrayList<>();

    public static void addUser(String name, int age, String username, String password, EPermission permission) throws UsernameExistException {
        if (isUsernameExist(username)) {
            throw new UsernameExistException("Username da ton tai!");
        }
        switch (permission) {
            case CUSTOMER -> users.add(new Customer(getNewID(), name, age, username, password));
            case STAFF -> {
            }
            case ADMIN -> {

            }
        };

    }

    public static User getUser(String username, String password) throws UsernameNotFoundException {
        var userList = UserManagement.users.stream().filter(user -> user.getUsername().equals(username) &&
                user.getPassword().equals(password)).toList();
        if (userList.size() == 0) {
            throw new UsernameNotFoundException("Username not found!");
        }
        return userList.get(0);
    }

    public static boolean isUsernameExist(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).toList().size() > 0;
    }

    public static String getNewID() {
        return Integer.toString(users.size());
    }

    public static void save() {
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

    public static void load() {
        File file = new File("./src/Data/User.bin");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
            users = (ArrayList<User>) oos.readObject();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
