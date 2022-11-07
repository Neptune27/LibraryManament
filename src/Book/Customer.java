package Book;

import User.EPermission;
import User.User;
import User.Errors.UsernameExistException;


public class Customer extends User {

    public Customer(String id, String name, int age, String username, String password) throws UsernameExistException {
        super(id, name, age, username, password, EPermission.CUSTOMER);
    }


    @Override
    public void generateMenu() {

    }
}
