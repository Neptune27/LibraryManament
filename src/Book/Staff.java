package Book;

import User.Errors.UsernameExistException;
import User.User;
import User.EPermission;

public class Staff extends User {

    public Staff(String id, String name, int age, String username, String password) throws UsernameExistException {
        super(id, name, age, username, password, EPermission.STAFF);
    }

    @Override
    public void generateMenu() {

    }
}
