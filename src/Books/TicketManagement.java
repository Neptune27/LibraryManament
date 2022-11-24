package Books;

import Customers.CustomerManagement;
import General.Common.ISaveLoad;
import General.Common.Date;
import General.Input.NInteger;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;
import User.StaffUser;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class TicketManagement implements ISaveLoad, IMenu {
    private ArrayList<BorrowedTicket> tickets = new ArrayList<>();
    private CustomerManagement customerManagement;
    private BookManagement bookManagement;
    private static int latestTicketId = 0;
    private static TicketManagement instance;

    public static void setLatestTicketId(int latestTicketId) {
        TicketManagement.latestTicketId = latestTicketId;
    }

    public static TicketManagement getInstance() {
        if (Objects.isNull(instance)) {
            instance = new TicketManagement();
        }
        return instance;
    }

    private TicketManagement() {
        load();
    }

    public void setBookManagement(BookManagement bookManagement) {
        this.bookManagement = bookManagement;
    }

    public void setCustomerManagement(CustomerManagement customerManagement) {
        this.customerManagement = customerManagement;
    }

    public int getNewTicketId() {
        latestTicketId++;
        return latestTicketId;
    }

    public boolean isBorrowed(Book book) {
        var now = new Date();
        for (var ticket : tickets) {
            if (Objects.equals(ticket.getBookID(), book.getID())) {
                var val = now.compareTo(ticket.getExpiredDate());
                if (val < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // region SAVE LOAD
    @Override
    public void save() {
        File file = new File("./src/Data/Ticket.bin");
        FileOutputStream fos;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file, false);
            out = new ObjectOutputStream(fos);
            out.writeObject(tickets);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setNewLatestTicketId() {
        try {
            int max = 0;
            for (var ticket : tickets) {
                max = Math.max(max, ticket.getTicketID());
            }
            setLatestTicketId(max);
        }
        catch (ClassCastException ig) {
            save();
        }

    }

    @Override
    public void load() {
        File file = new File("./src/Data/Ticket.bin");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
            tickets = (ArrayList<BorrowedTicket>) oos.readObject();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        finally {
            save();
            setNewLatestTicketId();
        }
    }


// endregion

    public void addNewTicketMenu(StaffUser staffUser) {
        var customer = customerManagement.getCustomerMenu();
        if (customer == null) {
            System.out.println("Khong co khach hang");
            return;
        }
        var book = bookManagement.getBookMenu();
        if (book == null) {
            System.out.println("Khong co sach");
            return;
        }
        int dateBorrowed = new NInteger("so ngay muon").getFromInput().getValue();
        int price = new NInteger("gia").getFromInput().getValue();
        var borrowedTicket = new BorrowedTicket(getNewTicketId(), customer.getId(), staffUser.getId(), book.getID(), new Date(), new Date().plusDay(dateBorrowed), price);
        tickets.add(borrowedTicket);
    }

    public void removeTicketMenu() {
        RunnableMenu menu = new RunnableMenu();
        menu.setRunOnce(true);
        for (var ticket : tickets) {
            menu.add(String.format("ID: %s, Customer ID: %s, Book ID %s, Start: %s, End: %s",ticket.getTicketID(), ticket.getCustomerID(), ticket.getBookID(), ticket.getDateBorrowed(), ticket.getExpiredDate()),
                    ()->tickets.remove(ticket));
        }
        menu.show();
    }

    public void menu(StaffUser staffUser) {
        RunnableMenu menu = new RunnableMenu("Quan ly phieu muon");
        menu.addBackgroundTask(this::save);
        menu.add("Thêm thẻ mượn", ()->addNewTicketMenu(staffUser));
        menu.add("Xoa the muon", this::removeTicketMenu);
        menu.show();
    }

    @Override
    public void menu() {

    }

}
