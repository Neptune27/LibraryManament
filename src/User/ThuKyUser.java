package User;

import General.Customer.Date;
import User.Errors.UsernameExistException;

import java.util.ArrayList;

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
    public ArrayList<Double> getHeSo() {
        ArrayList<Double> heSo = new ArrayList<>();
        heSo.add(1d);
        heSo.add(1.4d);
        heSo.add(1.8d);
        heSo.add(2.4d);
        heSo.add(3d);
        return heSo;
    }
}
