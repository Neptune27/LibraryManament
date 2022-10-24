package General.Menu;

import General.Input.NInteger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

abstract class Menu<T> {
    String name = "Value";
    final LinkedHashMap<String, T> functionLHMap = new LinkedHashMap<>();
    boolean pauseNext = true;


    public Menu() {}

    public Menu(String name) {
        this.name = name;
    }

    public void add(String name, T t) {
        functionLHMap.put(name, t);
    }

    public void setName(String name) {
        this.name = name.strip();
    }

    public void setPauseNext(boolean pauseNext) {
        this.pauseNext = pauseNext;
    }

    public void show() {
        var keys = new ArrayList<>(functionLHMap.keySet());
        var values = new ArrayList<>(functionLHMap.values());

        int inpInt;
        NInteger nInteger = new NInteger(name);
        do {
            System.out.println("------------------");

            for (int i = 0; i < keys.size(); i++) {
                System.out.println(i+1 + ": " + keys.get(i));
            }

            System.out.println("0: Return");
            System.out.println("------------------");


            inpInt = nInteger.getFromInput().getValue()-1;

            System.out.println("------------------");
            if (inpInt < keys.size() && inpInt >= 0)
                call(values.get(inpInt));
            if (pauseNext && inpInt != -1)
                pressEnterToContinue();
        } while (inpInt != -1);
    }

    private void pressEnterToContinue()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter key to continue...");
        try
        {
            scanner.nextLine();
        }
        catch(Exception ignored)
        {}
    }

    void call(T t) {
    }

}
