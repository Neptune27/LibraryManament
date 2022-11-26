package Books;

import General.Input.NString;
import General.Menu.RunnableMenu;
import User.ICreateFromInput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shelf implements Serializable, ICreateFromInput {
    private int shelfID;
    private String shelfName;
    private List<Book> books;

    public Shelf() {
        shelfID = BookManagement.getNewID();
        books = new ArrayList<>();
    }

    public int getShelfID() {
        return shelfID;
    }

    public void setShelfID(int shelfID) {
        this.shelfID = shelfID;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getBooks() {
        return books;
    }




    @Override
    public void setFromInput() {
        shelfName = new NString("tên kệ sách").getFromInput().getValue();
    }
}
