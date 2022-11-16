package Books;

import General.Customer.Date;

import java.io.Serializable;

public class BorrowedTicket implements Serializable {
    private int customerID;
    private int issueByID;
    private String bookID;
    private Date dateBorrowed;
    private Date expiredDate;

    public BorrowedTicket(int customerID, int issueByID, String bookID, Date dateBorrowed, Date expiredDate) {
        this.customerID = customerID;
        this.issueByID = issueByID;
        this.bookID = bookID;
        this.dateBorrowed = dateBorrowed;
        this.expiredDate = expiredDate;
    }
}
