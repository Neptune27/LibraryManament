package User;

import General.Input.NInteger;
import General.Input.NLong;
import General.Input.NString;
import General.Menu.RunnableMenu;

import java.io.Serializable;

public abstract class Person implements Serializable, ICreateFromInput {
    private String name;
    private int age;
    private ESex sex = ESex.NOT_SPECIFY;
    private String phoneNumber;
    private Address address;

    public Person() {

    }

    public Person(String name, int age, ESex sex, String phoneNumber, Address address) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    protected void setSexFromInput() {
        RunnableMenu menu = new RunnableMenu("Nhap gioi tinh");
        menu.setRunOnce(true);
        menu.add("Nam", ()->this.sex = ESex.MALE);
        menu.add("Nữ", ()->this.sex = ESex.FEMALE);
        menu.add("Không muốn tiết lộ", ()->this.sex = ESex.NOT_SPECIFY);
        menu.show();
    }

    protected void setPhoneNumber() {
        var phoneNumber = new NLong("số điện thoại").getFromInput().getValue();
        if (phoneNumber.toString().length() != 9) {
            System.out.println("SDT khong hop le");
            setPhoneNumber();
        }
        else {
            this.phoneNumber = "0" + phoneNumber.toString();
        }
    }

    public void setFromInput() {
        name = new NString("tên").getFromInput().getValue();
        age = new NInteger("tuổi").getFromInput().getValue();
        setSexFromInput();
        setPhoneNumber();
        address = new Address().setFromInput();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setSex(ESex sex) {
        this.sex = sex;
    }

    public ESex getSex() {
        return sex;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return  name +
                ", " + age +
                ", " + sex +
                ", " + phoneNumber +
                ", " + address;
    }
}
