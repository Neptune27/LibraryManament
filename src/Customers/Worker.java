package Customers;

import General.Input.NString;
import User.Address;
import User.ESex;

public class Worker extends Customer{
    String workPlace;

    public Worker(String name, int age, ESex sex, String phoneNumber, Address address, int id, String workPlace) {
        super(name, age, sex, phoneNumber, address, id, ECustomerType.STUDENT);
        this.workPlace = workPlace;
    }


    @Override
    public void setFromInput() {
        super.setFromInput();
        workPlace = new NString("nơi làm").getFromInput().getValue();
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + workPlace;
    }
}
