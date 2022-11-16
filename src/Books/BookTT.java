package Books;

public class BookTT extends Book {

    public BookTT(String ID, String bookName, String authorName, int ratedAge, boolean isBorrowed) {
        super(ID, bookName, authorName, ratedAge, isBorrowed);
        setMainType(EBookType.TIEU_THUYET);
    }
}
