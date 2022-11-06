package General.Customer;

public class Student extends Customer{
    private String school;

    public Student(String id, String name, int age, String email, Address address, String phone, String school) {
        super(id, name, age, email, address, phone);
        this.school = school;
    }

    public Student() {
        super();
        school = "";
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}
