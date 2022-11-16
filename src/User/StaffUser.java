package User;

import General.Customer.Date;
import General.Input.NDate;
import General.Input.NInteger;
import General.Input.NString;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;
import User.Errors.UsernameExistException;

public abstract class StaffUser extends Person implements IMenu, ISalary {
    private int id;
    private String username = "";
    private String password = "";
    private EPermission permission;
    private ERanking ranking;
    private int dayLeave;
    private EWorkShift workShift;
    private Date workStartDay;

    public StaffUser() {

    }

    public StaffUser(String name, int age, ESex sex, String phoneNumber, Address address,
                     int id, String username, String password,int dayLeave, EWorkShift workShift, Date workStartDay, EPermission permission) throws UsernameExistException {
        super(name, age, sex, phoneNumber, address);
        this.id = id;
        setUsername(username);
        this.password = password;
        this.permission = permission;
        this.workShift = workShift;
        this.dayLeave = dayLeave;
        this.workStartDay = workStartDay;
        setRanking();
    }

    public void setWorkShiftFromInput() {
        RunnableMenu menu = new RunnableMenu("Ca làm việc");
        menu.setRunOnce(true);
        menu.add("Sáng", () -> workShift = EWorkShift.MORNING);
        menu.add("Chiều", () -> workShift = EWorkShift.AFTERNOON);
        menu.add("Cả hai ", () -> workShift = EWorkShift.BOTH);
        menu.show();
    }

    public void setPermission(EPermission permission) {
        if (password.equals("")) {
            System.out.println("Vui long thiet lap mat khau truoc!");
        }
        this.permission = permission;
    }

    public EPermission getPermission() {
        return permission;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ERanking getRanking() {
        return ranking;
    }

    private void setRanking() {
        if (dayLeave <= 1)
            ranking = ERanking.A;
        else if (dayLeave <= 2)
            ranking = ERanking.B;
        else
            ranking = ERanking.C;
    }

    public void setRanking(ERanking ranking) {
        this.ranking = ranking;
    }

    public EWorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(EWorkShift workShift) {
        this.workShift = workShift;
    }

    public int getDayLeave() {
        return dayLeave;
    }

    public void setDayLeave(int dayLeave) {
        this.dayLeave = dayLeave;
        setRanking();
    }

    @Override
    public void setFromInput() {
        username = new NString("tên tài khoản").getFromInput().getValue();
        password = new NString("mật khẩu").getFromInput().getValue();
        super.setFromInput();
        id = UserManagement.getNewID();
        setWorkShiftFromInput();
        setDayLeave(new NInteger("ngày nghỉ").getFromInput().getValue());
        workStartDay = new NDate("ngày bắt đầu làm việc (dd-mm-yyyy)").getFromInput().getValue();
    }

    public void changePropertyMenu() {
        RunnableMenu menu = new RunnableMenu(getName());
        menu.add("Thay tên", ()->setName(new NString("tên").getFromInput().getValue()));
        menu.add("Thay tuổi", ()->setAge(new NInteger("tuổi").getFromInput().getValue()));
        menu.add("Thay giới tính", this::setSexFromInput);
        menu.add("Thay SDT", this::setPhoneNumber);
        menu.add("Thay địa chỉ", ()->setAddress(new Address().setFromInput()));
        menu.add("Thay ngày nghỉ", ()->setDayLeave(new NInteger("ngày ").getFromInput().getValue()));
        menu.add("Thay ca làm", this::setWorkShiftFromInput);
        menu.show();
    }

    public void setWorkStartDay(Date workStartDay) {
        this.workStartDay = workStartDay;
    }

    public Date getWorkStartDay() {
        return workStartDay;
    }

    @Override
    public String toString() {
        return  id +
                ", " + username +
                ", " + password + ", " +
                super.toString() +
                ", " + permission +
                ", " + ranking +
                ", " + dayLeave +
                ", " + workShift +
                ", " + workStartDay +
                '\n';
    }
}
