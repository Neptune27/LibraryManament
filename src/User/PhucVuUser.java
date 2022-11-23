package User;

import General.Customer.Date;
import User.Errors.UsernameExistException;

import java.util.ArrayList;

public class PhucVuUser extends StaffUser {

    public PhucVuUser() {

    }

    public PhucVuUser(String name, int age, ESex sex, String phoneNumber, Address address, int id, String username, String password, int dayLeave, EWorkShift workShift, Date workStartDay) throws UsernameExistException {
        super(name, age, sex, phoneNumber, address, id, username, password, dayLeave, workShift, workStartDay, EPermission.PHUC_VU);
    }

    @Override
    public void setFromInput() {
        super.setFromInput();
        setPermission(EPermission.PHUC_VU);
    }

    @Override
    public void menu() {

    }

    @Override
    public ArrayList<Double> getHeSo() {
        ArrayList<Double> heSo = new ArrayList<>();
        heSo.add(1d);
        heSo.add(1.2d);
        heSo.add(1.4d);
        heSo.add(2d);
        heSo.add(2.5d);
        return heSo;
    }
}
