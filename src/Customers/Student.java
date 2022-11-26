package Customers;

import General.Input.NString;
import General.Menu.RunnableMenu;
import User.Address;
import User.ESex;

public class Student extends Customer{
    String school;

    public Student(){};

    public Student(String name, int age, ESex sex, String phoneNumber, Address address, int id, String school) {
        super(name, age, sex, phoneNumber, address, id, ECustomerType.STUDENT);
        this.school = school;
    }

    @Override
    public void setFromInput() {
        super.setFromInput();
        setCType(ECustomerType.STUDENT);
        school = new NString("trường").getFromInput().getValue();
    }

    @Override
    public RunnableMenu makeChangeMenu() {
        RunnableMenu menu = super.makeChangeMenu();
        menu.addSection("Student");
        menu.add("Thay truong", ()->school = new NString("truong").getFromInput().getValue());
        return menu;
    }

    @Override
    public void changePropertiesMenu() {
        makeChangeMenu().show();
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

    @Override
    public String getWork() {
        return getSchool();
    }
}
