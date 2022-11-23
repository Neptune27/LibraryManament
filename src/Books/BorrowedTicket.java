package Books;

import General.Common.Date;

import java.io.Serializable;

public class BorrowedTicket implements Serializable {
    private int ticketID;
    private int customerID;
    private int issueByID;
    private String bookID;
    private Date dateBorrowed;
    private Date expiredDate;
    private int price;

    public BorrowedTicket(int ticketID,int customerID, int issueByID, String bookID, Date dateBorrowed, Date expiredDate, int price) {
        this.ticketID = ticketID;
        this.customerID = customerID;
        this.issueByID = issueByID;
        this.bookID = bookID;
        this.dateBorrowed = dateBorrowed;
        this.expiredDate = expiredDate;
        this.price = price;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setDateBorrowed(Date dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setIssueByID(int issueByID) {
        this.issueByID = issueByID;
    }

    public int getIssueByID() {
        return issueByID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
