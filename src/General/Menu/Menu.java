package General.Menu;

import General.Input.NInteger;

import java.util.*;

abstract class Menu<T> {
    String name = "vị trí";
    String title = "";
    final LinkedHashMap<String, T> functionLHMap = new LinkedHashMap<>();
    final HashSet<T> backgroundTasks = new HashSet<>();
    final HashSet<T> onReturnTask = new HashSet<>();
    final LinkedHashMap<Integer, String> section = new LinkedHashMap<>();
    boolean pauseNext = true;
    boolean runOnce = false;
    int res = -99;

    public int getRes() {
        return res;
    }

    public void removeItem(String keyName) {
        functionLHMap.remove(keyName);
    }

    public Menu() {}

    public Menu(String title) {
        this.title = title;
    }

    public void clear() {
        functionLHMap.clear();
        backgroundTasks.clear();
        section.clear();
        pauseNext = true;
        runOnce = false;
    }

    public void add(String name, T t) {
        functionLHMap.put(name, t);
    }

    public void addSection(String name) {
        section.put(functionLHMap.size(), name);
    }

    public void addBackgroundTask(T task) {
        backgroundTasks.add(task);
    }

    public void addOnReturnTask(T task) {
        onReturnTask.add(task);
    }

    public void setName(String name) {
        this.name = name.strip();
    }

    public void setPauseNext(boolean pauseNext) {
        this.pauseNext = pauseNext;
    }

    public void setRunOnce(boolean runOnce) {
        this.runOnce = runOnce;
        setPauseNext(false);
    }

    private String makeNEqual(int n) {
        return new String(new char[n]).replace("\0", "=");
    }

    public void show() {

        var keys = new ArrayList<>(functionLHMap.keySet());
        var values = new ArrayList<>(functionLHMap.values());

        res = -99;
        NInteger nInteger = new NInteger(name);
        do {
            for(var task : backgroundTasks) {
                call(task);
            }

            System.out.printf("=======%s=======\n", title);

            for (int i = 0; i < keys.size(); i++) {
                if (section.containsKey(i)) {
                    System.out.printf("---%s---\n",section.get(i));
                }
                System.out.println(i+1 + ": " + keys.get(i));
            }

            if (keys.size() == 0) {
                System.out.println("Khong co du lieu!");
                System.out.printf("=======%s=======\n", makeNEqual(title.length()));
                break;
            }
            if (!(runOnce))
                System.out.println("0: Quay ra");
            System.out.printf("=======%s=======\n", makeNEqual(title.length()));

            res = nInteger.getFromInput().getValue()-1;

            System.out.println("------------------");
            if (res < keys.size() && res >= 0)
                call(values.get(res));
            else if (res != -1) {
                System.out.println("Vui lòng nhập giá trị hợp lệ.");
                continue;
            }
            else {
                if (runOnce) {
                    res = -99;
                    continue;
                }
            }

            if (pauseNext && res != -1)
                pressEnterToContinue();


            if (runOnce) {
                break;
            }
        } while (res != -1);

        for(var task : onReturnTask) {
            call(task);
        }
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