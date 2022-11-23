package Books;

import General.Input.NInteger;
import General.Input.NString;
import General.Menu.RunnableMenu;
import User.ICreateFromInput;

import java.io.Serializable;

public abstract class Book implements Serializable, ICreateFromInput {
    private String ID;
    private String bookName;
    private String authorName;
    private int ratedAge;
    private boolean isBorrowed;
    private EBookType mainType;
    private EBookType subType;

    public Book(){};

    public Book(String ID, String bookName, String authorName, int ratedAge, boolean isBorrowed) {
        this.ID = ID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.ratedAge = ratedAge;
        this.isBorrowed = isBorrowed;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setRatedAge(int ratedAge) {
        this.ratedAge = ratedAge;
    }

    public int getRatedAge() {
        return ratedAge;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public EBookType getMainType() {
        return mainType;
    }

    public void setMainType(EBookType mainType) {
        this.mainType = mainType;
    }

    public EBookType getSubType() {
        return subType;
    }

    public void setSubType(EBookType subType) {
        this.subType = subType;
    }

    private EBookType setBookTypeFromInput(String name) {
        final EBookType[] type = new EBookType[1];

        RunnableMenu menu = new RunnableMenu(name);
        menu.setRunOnce(true);
        menu.add("Tâm lý", ()->{
            type[0] = EBookType.TAM_LY;});
        menu.add("Thiếu nhi", ()->{
            type[0] = EBookType.THIEU_NHI;});
        menu.add("Tiểu thuyết", ()->{
            type[0] = EBookType.TIEU_THUYET;});
        menu.add("Văn học", ()->{
            type[0] = EBookType.VAN_HOC;});
        menu.add("Kh", ()->{
            type[0] = EBookType.NONE;});
        menu.show();

        return type[0];
    }

    @Override
    public void setFromInput() {
        ID = new NString("ID").getFromInput().getValue();
        bookName= new NString("tên sách").getFromInput().getValue();
        authorName = new NString("tên tác giả").getFromInput().getValue();
        ratedAge = new NInteger("độ tuổi phù hợp").getFromInput().getValue();
        subType = setBookTypeFromInput("Chủ dề phụ");
    }
}
