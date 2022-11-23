package User;

import java.util.ArrayList;

//Maybe them cap bac luong
public interface ISalary {

    ArrayList<Double> getHeSo();
    long getSalary();
    void updateBonus();
    long getTotalSalary();
}
