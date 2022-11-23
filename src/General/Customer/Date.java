package General.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

public class Date implements Serializable {
    LocalDate date;

    public Date(int day, int month, int year) {
        date = LocalDate.of(year, month, day);
    }

    public Date() {
        date = LocalDate.now();
    }

    public Date plusDay(int dayToAdd) {
        date = date.plusDays(dayToAdd);
        return this;
    }

    public double compareTo(Date dateToCompare) {
        return date.toEpochDay() - dateToCompare.date.toEpochDay();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return Objects.equals(date, date1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    public Date(String s){
        String a_s[] = s.split("-");
        var day = Integer.parseInt(a_s[0]);
        var month = Integer.parseInt(a_s[1]);
        var year = Integer.parseInt(a_s[2]);
        try {
            date = LocalDate.of(year, month, day);
        } catch (Exception ignored) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}
