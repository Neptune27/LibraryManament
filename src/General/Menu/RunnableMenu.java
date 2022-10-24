package General.Menu;

public class RunnableMenu extends Menu<CheckedRunnable<Exception>> {

    public RunnableMenu() {}

    public RunnableMenu(String name) {
        super(name);
    }

    @Override
    void call(CheckedRunnable<Exception> func) {
        func.run();
    }
}
