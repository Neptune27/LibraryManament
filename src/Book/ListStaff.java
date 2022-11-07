package Book;

import User.Errors.UsernameExistException;

import java.util.ArrayList;
import java.util.Scanner;

public class ListStaff {
    public static ArrayList<Staff> listStaff;

    public static void them() throws UsernameExistException {
        Scanner sc = new Scanner(System.in);
        Staff staff = new Staff(sc.nextLine(),sc.nextLine(),Integer.parseInt(sc.nextLine()),sc.nextLine(),sc.nextLine());
        listStaff.add(staff);
    }
}
