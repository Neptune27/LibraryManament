package Example.PERSONNEL_MANAGER;

import java.io.Serializable;
import java.util.Scanner;

public class DiaChi implements Serializable {
    private String prov;
    private String dist;
    Scanner scanner = new Scanner(System.in);
    public DiaChi(){}

    public DiaChi(String prov, String dist){
        this.prov = prov;
        this.dist = dist;
    }

    public String getProv() {
        return prov;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }
    public void nhap(){
        System.out.printf("Nhap Huyen/Quan: ");
        dist= scanner.nextLine();
        System.out.printf("Nhap Tinh/Thanh Pho: ");
        prov = scanner.nextLine();
    }
    @Override
    public String toString() {
        return dist +", " +prov;
    }
}
