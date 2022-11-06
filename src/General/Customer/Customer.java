package General.Customer;

public class Customer {
    //attribute
    protected String id;
    protected String name;
    protected int age;
    protected String email;
    protected Address address;
    protected String phone;
    //Constructor
    public Customer(String id, String name, int age, String email, Address address, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public Customer() {
        id = "";
        name = "";
        age = 18;
        email = "";
        address = new Address();
        phone = "";
    }
    //setter and getter
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //method
    public void inputInfo(){
        InputInformation inp = new InputInformation();
        System.out.println("Nhập thông tin khách hàng!");
        id = inp.inputCustomerID();
        name = inp.inputName();

    }
}
