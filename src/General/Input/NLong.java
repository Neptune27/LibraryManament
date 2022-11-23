package General.Input;

import java.util.Scanner;

public class NLong extends NInstance<Long> {
    public NLong() {
        super();
    }

    public NLong(String name) {
        super(name);
    }

    @Override
    protected void errorMessage() {
        System.out.println("Wrong input, please input an integer!");
    }

    @Override
    protected Long scanNext(Scanner scanner) {
        return scanner.nextLong();
    }

}
