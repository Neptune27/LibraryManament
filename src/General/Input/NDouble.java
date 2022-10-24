package General.Input;

import java.util.Scanner;

public class NDouble extends NInstance<Double> {
    public NDouble() {
        super();
    }

    public NDouble(String name) {
        super(name);
    }

    @Override
    protected void errorMessage() {
        System.out.println("Wrong input, please input a real number!");
    }

    @Override
    protected Double scanNext(Scanner scanner) {
        return scanner.nextDouble();
    }
}