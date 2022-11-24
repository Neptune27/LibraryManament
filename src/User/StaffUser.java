package User;

import General.Common.Date;
import General.Input.NDate;
import General.Input.NDouble;
import General.Input.NInteger;
import General.Input.NString;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;

public abstract class StaffUser extends Person implements IMenu, ISalary {
    private int id;
    private String username = "";
    private String password = "";
    private EPermission permission;
    private ERanking ranking;
    private int dayLeave;
    private EWorkShift workShift;
    private Date workStartDay;
    private double salary;
    private double bonus;

    public StaffUser() {

    }

    public StaffUser(String name, int age, ESex sex, String phoneNumber, Address address,
                     int id, String username, String password,int dayLeave, EWorkShift workShift, Date workStartDay, EPermission permission) {
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
        salary = new NDouble("luong").getFromInput().getValue();
    }

    @Override
    public RunnableMenu makeChangeMenu() {
        RunnableMenu menu = super.makeChangeMenu();
        menu.addSection("Staff User");
        menu.add("Thay ngày nghỉ", ()->setDayLeave(new NInteger("ngày ").getFromInput().getValue()));
        menu.add("Thay ca làm", this::setWorkShiftFromInput);
        return menu;
    }

    public void changePropertyMenu() {
        RunnableMenu menu = makeChangeMenu();

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

    public String getBasicInfo() {
        return id +
                ", " + username +
                ", " + getName() +
                ", " + getAge();
    }


    @Override
    public void updateBonus() {
        if (getRanking() == ERanking.A) {
            bonus = getSalary() * 0.05;
        }
        else {
            bonus = 0;
        }
    }

    public long getSalary() {
        var heSo = getHeSo();
        int max = heSo.size();
        var hsIndex = (new Date()).compareTo(workStartDay) / 365;
        if (hsIndex >= max) {
            return (long) (salary * heSo.get(max-1));
        }
        return (long) (salary * heSo.get((int) hsIndex));

    }

    public double getBonus() {
        updateBonus();
        return bonus;
    }

    @Override
    public long getTotalSalary() {
        return (long) (getSalary() + getBonus());
    }
}
