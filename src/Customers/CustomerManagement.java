package Customers;


import General.Common.ISaveLoad;
import User.StaffUser;

import java.io.*;
import java.util.ArrayList;

public class CustomerManagement implements ISaveLoad {
    public ArrayList<Customer> customers = new ArrayList<>();
    private static int latestUserId = 0;

    public static void setLatestUserId(int latestUserId) {
        CustomerManagement.latestUserId = latestUserId;
    }

    public static int getNewID() {
        latestUserId++;
        return latestUserId;
    }


    @Override
    public void save() {
        File file = new File("./src/Data/User.bin");
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file, false);
            out = new ObjectOutputStream(fos);
            out.writeObject(customers);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setNewLatestUserId() {
        int max = 0;
        for (var customer : customers) {
            max = Math.max(max, customer.getId());
        }
        setLatestUserId(max);
    }

    @Override
    public void load() {
        File file = new File("./src/Data/User.bin");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
            customers = (ArrayList<Customer>) oos.readObject();
            fis.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        finally {
            setNewLatestUserId();
        }
    }
}
