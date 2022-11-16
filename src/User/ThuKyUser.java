package User;

import General.Customer.Date;
import User.Errors.UsernameExistException;

public class ThuKyUser extends StaffUser {

    public ThuKyUser() {
        super();
    }

    public ThuKyUser(String name, int age, ESex sex, String phoneNumber, Address address, int id, String username, String password, int dayLeave, EWorkShift workShift, Date workStartDay) throws UsernameExistException {
        super(name, age, sex, phoneNumber, address, id, username, password, dayLeave, workShift, workStartDay, EPermission.THU_KY);
    }

    @Override
    public void setFromInput() {
        super.setFromInput();
        setPermission(EPermission.THU_KY);
    }

    @Override
    public void menu() {

    }


    @Override
    public double salary() {
        return 0;
    }

    @Override
    public double bonus() {
        return 0;
    }

    @Override
    public double totalSalary() {
        return 0;
    }

    @Override
    public void printSalary() {

    }
}
