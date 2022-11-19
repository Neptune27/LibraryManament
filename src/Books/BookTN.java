package Books;

public class BookTN extends Book {
    public BookTN(){
        setMainType(EBookType.THIEU_NHI);
    };

    public BookTN(String ID, String bookName, String authorName, int ratedAge, boolean isBorrowed) {
        super(ID, bookName, authorName, ratedAge, isBorrowed);
        setMainType(EBookType.THIEU_NHI);
    }
}
