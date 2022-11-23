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


    public void addBookFromInput() {
        RunnableMenu menu = new RunnableMenu("Thêm Sách Trong Kệ " + shelfName);
        menu.add("Sách Tâm Lý", ()->{
            BookTL bookTL = new BookTL();
            bookTL.setFromInput();
            books.add(bookTL);
        });
        menu.add("TIểu thuyết", ()->{
            BookTT bookTT = new BookTT();
            bookTT.setFromInput();
            books.add(bookTT);
        });
        menu.add("Sách thiếu nhi", ()->{
            BookTN bookTN = new BookTN();
            bookTN.setFromInput();
            books.add(bookTN);
        });
        menu.add("Sách văn học", ()->{
            BookVHNT bookVHNT = new BookVHNT();
            bookVHNT.setFromInput();
            books.add(bookVHNT);
        });
        menu.show();
    }

    @Override
    public void setFromInput() {
        shelfName = new NString("tên kệ sách").getFromInput().getValue();
    }
}
