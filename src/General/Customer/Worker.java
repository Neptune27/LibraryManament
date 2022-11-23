package General.Customer;

public class Worker extends Customer{

    private String workPlace;
    private static String type;
    private static int workerAmount = 0;

    static {
        type = "Công nhân viên";
    }

    public Worker(String id, String name, Address address, String phone, Date birthday, String workPlace) {
        super(id, name, address, phone, birthday);
        workerAmount ++;
        this.workPlace = workPlace;
    }

    public static int getWorkerAmount() {
        return workerAmount;
    }

    public static void setWorkerAmount(int workerAmount) {
        Worker.workerAmount = workerAmount;
    }

    public Worker() {
        workerAmount ++;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Worker.type = type;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id='" + super.getId() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", address=" + super.getAddress() +
                ", phone='" + super.getPhone() + '\'' +
                ", birthday=" + super.getBirthday().toString() +'\'' +
                ", workPlace='" + workPlace +
                "'}";
    }
}
