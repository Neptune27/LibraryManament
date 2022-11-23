package General.Customer;

import java.io.Serializable;


public abstract class Customer implements Serializable {
    private String id;
    private String name;
    private Address address;
    private static int amount = 0;
    private String phone;
    private Date birthday;

    public Customer(String id, String name, Address address, String phone, Date birthday) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        amount ++;
    }

    public Customer() {
        id = "";
        name = "";
        address = null;
        phone = "";
        birthday = null;
        amount ++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static int getAmount() {
        return amount;
    }

    public static void setAmount(int amount) {
        Customer.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                '}';
    }

}