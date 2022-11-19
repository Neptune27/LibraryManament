package General.Customer;

import General.Menu.RunnableMenu;

public class Student extends Customer{
    private String school;
    private static String type;
    private static int studentAmount = 0;

    static {
        type = "Học sinh/ sinh viên";
    }

    public Student(String id, String name, Address address, String phone, Date birthday, String school) {
        super(id, name, address, phone, birthday);
        studentAmount ++;
        this.school = school;
    }

    public Student() {
        studentAmount ++;
    }

    public static int getStudentAmount() {
        return studentAmount;
    }

    public static void setStudentAmount(int studentAmount) {
        Student.studentAmount = studentAmount;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Student.type = type;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + super.getId() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", address=" + super.getAddress() +
                ", phone='" + super.getPhone() + '\'' +
                ", birthday=" + super.getBirthday().toString() +'\'' +
                ", school='" + school +
                "'}";
    }
}
