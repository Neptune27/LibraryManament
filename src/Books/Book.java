package Books;

import java.io.Serializable;

public abstract class Book implements Serializable {
    private String ID;
    private String bookName;
    private String authorName;
    private int ratedAge;
    private boolean isBorrowed;
    private EBookType mainType;
    private EBookType subType;

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
}
