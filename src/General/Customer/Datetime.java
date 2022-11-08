package General.Customer;

import java.io.Serializable;

public class Datetime implements Serializable {
    private int day;
    private int month;
    private int year;

    public Datetime(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Datetime() {
        day = 0;
        month = 0;
        year = 0;
    }
    public Datetime(String s){
        String a_s[] = s.split("-");
        day = Integer.parseInt(a_s[0]);
        month = Integer.parseInt(a_s[1]);
        year = Integer.parseInt(a_s[2]);
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/"+ month+ "/"+year;
    }
}
