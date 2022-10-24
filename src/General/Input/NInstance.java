package General.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class NInstance<T> {
    T value;
    String name = "n";

    public NInstance() {
    }

    public NInstance(String name) {
        this.name = name;
    }


    public T getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NInstance<T> getFromInput() {
        System.out.print("Input " + name + ": ");
        var scanner = new Scanner(System.in);
        try {
            value = scanNext(scanner);
        }
        catch (InputMismatchException ex) {
            errorMessage();
            getFromInput();
        }
        return this;
    }

    protected void errorMessage() {
    }

    protected T scanNext(Scanner scanner) {
        return null;
    }

    public NInstance(T n) {
        value = n;
    }
}

