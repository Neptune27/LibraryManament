package Customers;

import General.Menu.RunnableMenu;
import User.Address;
import User.ESex;
import User.Person;

public class Customer extends Person {
    private int id;
    private ECustomerType cType;

    public Customer(){};

    public Customer(String name, int age, ESex sex, String phoneNumber, Address address,
        int id, ECustomerType cType) {
        super(name, age, sex, phoneNumber, address);
        this.id = id;
        this.cType = cType;
    }

    protected void setCType(ECustomerType cType) {
        this.cType = cType;
    }

    protected void setCTypeFromInput() {
        RunnableMenu menu = new RunnableMenu("giới tính");
        menu.setRunOnce(true);
        menu.add("Bình thường", ()->cType = ECustomerType.NORMAL);
        menu.add("Nhân viên viên chức", ()->cType = ECustomerType.WORKER);
        menu.add("Học sinh", ()->cType = ECustomerType.STUDENT);
        menu.show();
    }

    @Override
    public void setFromInput() {
        super.setFromInput();
        id = CustomerManagement.getNewID();
    }

    public void changePropertiesMenu() {
        RunnableMenu menu = makeChangeMenu();
        menu.show();
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return  id +
                ", " + cType + ", " + super.toString();
    }

    public String getBasicInfo() {
        return getId() + ", " + getName() + ", " + String.valueOf(getAge()) + ", " + getPhoneNumber();

    }


    public String getWork() {
        return "";
    }
}
