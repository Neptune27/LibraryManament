package Customers;

import General.Input.NString;
import User.Address;
import User.ESex;

public class Student extends Customer{
    String school;

    public Student(String name, int age, ESex sex, String phoneNumber, Address address, int id, String school) {
        super(name, age, sex, phoneNumber, address, id, ECustomerType.STUDENT);
        this.school = school;
    }

    @Override
    public void setFromInput() {
        super.setFromInput();
        school = new NString("trường").getFromInput().getValue();
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + school;
    }
}
