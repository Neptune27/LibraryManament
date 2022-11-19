package Books;

public class BookTL extends Book {
    public BookTL(){
        setMainType(EBookType.TAM_LY);
    };

    public BookTL(String ID, String bookName, String authorName, int ratedAge, boolean isBorrowed) {
        super(ID, bookName, authorName, ratedAge, isBorrowed);
        setMainType(EBookType.TAM_LY);
    }
}
