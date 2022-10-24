package Example;

import General.Input.NDouble;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;

public class HINHCHUNHAT implements IMenu {
    double height;
    double width;

    final NDouble heightInput = new NDouble("Height");
    final NDouble widthInput = new NDouble("Width");

    public HINHCHUNHAT() {
    }

    public void input() {
        height = heightInput.getFromInput().getValue();
        width = widthInput.getFromInput().getValue();
    }

    public void setHeightFromInput() {
        height = heightInput.getFromInput().getValue();

        
    }

    public void setWidthFromInput() {
        width = widthInput.getFromInput().getValue();

        
    }

    public void printProperty() {
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);

        
    }

    private Double getArea() {
        return width*height;
    }

    public void printArea() {
        System.out.println("Area: " + getArea());
    }

    @Override
    public void generateMenu() {
        RunnableMenu menu = new RunnableMenu();
        menu.add("Input", this::input);
        menu.add("Show Property", this::printProperty);
        menu.add("Set height", this::setHeightFromInput);
        menu.add("Set width", this::setWidthFromInput);
        menu.add("Get Area", this::printArea);
        menu.show();
    }

    public static void main(String[] args) {
        RunnableMenu menu = new RunnableMenu();
        HINHCHUNHAT hcn = new HINHCHUNHAT();
        menu.add("HCM", hcn::generateMenu);
        menu.add("Print Hello world inline", ()-> System.out.println("Hello World!"));
        menu.add("Disable Press Enter", ()->menu.setPauseNext(false));
        menu.show();
    }
}
