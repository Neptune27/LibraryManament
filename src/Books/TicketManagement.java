package Books;

import Customers.Customer;
import Customers.CustomerManagement;
import General.Common.ISaveLoad;
import General.Customer.Date;
import General.Input.NInteger;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;
import User.StaffUser;

import java.io.*;
import java.util.ArrayList;

public class TicketManagement implements ISaveLoad, IMenu {
    private ArrayList<BorrowedTicket> tickets = new ArrayList<>();
    private final CustomerManagement customerManagement = CustomerManagement.getInstance();
    private final BookManagement bookManagement = BookManagement.getInstance();
    private static int latestTicketId = 0;

    public static void setLatestTicketId(int latestTicketId) {
        TicketManagement.latestTicketId = latestTicketId;
    }

    public int getNewTicketId() {
        latestTicketId++;
        return latestTicketId;
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
        int max = 0;
        for (var ticket : tickets) {
            max = Math.max(max, ticket.getTicketID());
        }
        setLatestTicketId(max);
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
        var borrowedTicket = new BorrowedTicket(getNewTicketId(), customer.getId(), staffUser.getId(), book.getID(), new Date(), new Date().plusDay(dateBorrowed));
        tickets.add(borrowedTicket);
    }

    public void removeTicketMenu() {
        RunnableMenu menu = new RunnableMenu();
        menu.setRunOnce(true);
        for (var ticket : tickets) {
            menu.add(String.format("ID: %i, Customer ID: %i, Book ID %s, Start: %s, End: %s",ticket.getTicketID(), ticket.getCustomerID(), ticket.getBookID(), ticket.getDateBorrowed(), ticket.getExpiredDate()),
                    ()->tickets.remove(ticket));
        }
        menu.show();
    }

    public void menu(StaffUser staffUser) {
        RunnableMenu menu = new RunnableMenu("Quan ly phieu muon");
        menu.addBackgroundTask(this::save);

    }

    @Override
    public void menu() {

    }

}
