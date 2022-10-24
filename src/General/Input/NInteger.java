package General.Input;

import java.util.Scanner;

public class NInteger extends NInstance<Integer> {
    public NInteger() {
        super();
    }

    public NInteger(String name) {
        super(name);
    }

    @Override
    protected void errorMessage() {
        System.out.println("Wrong input, please input an integer!");
    }

    @Override
    protected Integer scanNext(Scanner scanner) {
        return scanner.nextInt();
    }

}
