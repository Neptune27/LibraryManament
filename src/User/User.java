package User;

import General.Menu.IMenu;
import User.Errors.UsernameExistException;

public abstract class User extends Person implements IMenu {
    private String id;
    private String username = "";
    private String password = "";
    private EPermission permission;


    public User(String name, int birthYear, String username, String password, EPermission permission) throws UsernameExistException {
        super(name, birthYear);
        this.id = UserManagement.getNewID();
        setUsername(username);
        this.password = password;
        this.permission = permission;
    }

    public User(String id, String name, int age, String username, String password, EPermission permission) throws UsernameExistException {
        super(name, age);
        this.id = id;
        setUsername(username);
        this.password = password;
        this.permission = permission;
    }


    public void setPermission(EPermission permission, User personSet) {
        if (password.equals("")) {
            System.out.println("Vui long thiet lap mat khau truoc!");
        }
        if (personSet.permission == EPermission.STAFF) {
            this.permission = permission;
        }
    }

    public EPermission getPermission() {
        return permission;
    }

    public void setUsername(String username) {
        var isExist = UserManagement.isUsernameExist(username);
        if (!isExist)
            this.username = username;
        else
            System.out.println("Ten tk da ton tai!");
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                '}';
    }
}
