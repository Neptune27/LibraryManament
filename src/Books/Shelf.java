package Books;

import java.io.Serializable;
import java.util.List;

public class Shelf implements Serializable {
    private int shelfID;
    private String shelfName;
    private List<Book> books;
}
