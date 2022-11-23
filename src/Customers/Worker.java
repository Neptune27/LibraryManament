package Customers;

import General.Input.NString;
import General.Menu.RunnableMenu;
import User.Address;
import User.ESex;

public class Worker extends Customer{
    Address workPlace;

    public Worker(){

    }

    public Worker(String name, int age, ESex sex, String phoneNumber, Address address, int id, Address workPlace) {
        super(name, age, sex, phoneNumber, address, id, ECustomerType.WORKER);
        this.workPlace = workPlace;
    }


    @Override
    public void setFromInput() {
        super.setFromInput();
        setCType(ECustomerType.WORKER);
        System.out.println("Noi lam");
        workPlace = new Address().setFromInput();
    }

    public void setWorkPlace(Address workPlace) {
        this.workPlace = workPlace;
    }

    public Address getWorkPlace() {
        return workPlace;
    }

    @Override
    public RunnableMenu makeChangeMenu() {
        RunnableMenu menu = super.makeChangeMenu();
        menu.addSection("Student");
        menu.add("Thay truong", ()->workPlace = new Address().setFromInput());
        return menu;
    }

    @Override
    public void changePropertiesMenu() {
        makeChangeMenu().show();
    }

    @Override
    public String toString() {
        return super.toString() + ", " + workPlace;
    }
}
