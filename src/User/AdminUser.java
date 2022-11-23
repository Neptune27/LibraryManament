package User;

import General.Common.Date;

import java.util.ArrayList;

public class AdminUser extends StaffUser {


    public AdminUser(String name, int age, ESex sex, String phoneNumber, Address address, int id, String username, String password, int dayLeave, EWorkShift workShift, Date workStartDay) {
        super(name, age, sex, phoneNumber, address, id, username, password, dayLeave, workShift, workStartDay, EPermission.ADMIN);
    }

    public AdminUser(){
        super();

    };

    @Override
    public void setFromInput() {
        super.setFromInput();
        setPermission(EPermission.ADMIN);
    }



    @Override
    public void menu() {

    }


    @Override
    public ArrayList<Double> getHeSo() {
        ArrayList<Double> heSo = new ArrayList<>();
        heSo.add(1d);
        heSo.add(1.5d);
        heSo.add(2d);
        heSo.add(2.5d);
        heSo.add(3d);
        return heSo;
    }
}
