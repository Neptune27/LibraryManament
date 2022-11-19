package General.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Date implements Serializable {
    LocalDate date;

    public Date(int day, int month, int year) {
        date = LocalDate.of(year, month, day);
    }

    public Date() {
        date = LocalDate.now();
    }

    public Date plusDay(int dayToAdd) {
        date.plusDays(dayToAdd);
        return this;
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
