package General.Input;

import General.Common.Date;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NDate extends NInstance<Date> {
    public NDate() {
        super();
    }

    public NDate(String name) {
        super(name);
    }

    @Override
    protected void errorMessage() {
        System.out.println("Wrong input, please a date in dd-mm-yyyy format!");
    }

    @Override
    protected Date scanNext(Scanner scanner) {
        try {
            return new Date(scanner.nextLine());
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new InputMismatchException();
        }
    }
}