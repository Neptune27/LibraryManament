package General.Menu;

import General.Input.NInteger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

abstract class Menu<T> {
    String name = "Value";
    String title = "";
    final LinkedHashMap<String, T> functionLHMap = new LinkedHashMap<>();
    final LinkedHashMap<Integer, String> section = new LinkedHashMap<>();
    boolean pauseNext = true;


    public Menu() {}

    public Menu(String title) {
        this.title = title;
    }

    public void add(String name, T t) {
        functionLHMap.put(name, t);
    }

    public void addSection(String name) {
        section.put(functionLHMap.size(), name);
    }

    public void setName(String name) {
        this.name = name.strip();
    }

    public void setPauseNext(boolean pauseNext) {
        this.pauseNext = pauseNext;
    }

    private String makeNEqual(int n) {
        return new String(new char[n]).replace("\0", "=");
    }

    public void show() {
        var keys = new ArrayList<>(functionLHMap.keySet());
        var values = new ArrayList<>(functionLHMap.values());

        int inpInt;
        NInteger nInteger = new NInteger(name);
        do {
            System.out.printf("=======%s=======\n", title);

            for (int i = 0; i < keys.size(); i++) {
                if (section.containsKey(i)) {
                    System.out.printf("---%s---\n",section.get(i));
                }
                System.out.println(i+1 + ": " + keys.get(i));
            }

            System.out.println("0: Return");
            System.out.printf("=======%s=======\n", makeNEqual(title.length()));

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