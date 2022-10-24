package General.Input;

import java.util.Scanner;

public class NString extends NInstance<String> {
    public NString() {
        super();
    }

    public NString(String name) {
        setName(name);
    }

    @Override
    protected void errorMessage() {
        System.out.println("Wrong input, please input a String!");
    }

    @Override
    protected String scanNext(Scanner scanner) {
        return scanner.nextLine();
    }
}