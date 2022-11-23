package Books;

public class BookVHNT extends Book {
    public BookVHNT(){
        setMainType(EBookType.VAN_HOC);
    };

    public BookVHNT(String ID, String bookName, String authorName, int ratedAge, boolean isBorrowed) {
        super(ID, bookName, authorName, ratedAge, isBorrowed);
        setMainType(EBookType.VAN_HOC);
    }
}
