package General.Customer;

import java.io.Serializable;
import java.util.Scanner;

public class Address implements Serializable {
    //Attribute
    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    //Constructor
    public Address(String houseNumber, String street, String ward, String district) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.ward = ward;
        this.district = district;
    }

    public Address() {
        houseNumber = "";
        street = "";
        ward = "";
        district = "";
    }
    public Address(String s){
        String a_s[] = s.split(",");
        houseNumber = a_s[0];
        street = a_s[1];
        ward = a_s[2];
        district = a_s[3];
    }
    //Setter and getter
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return houseNumber +","+street+","+ward+","+district;
    }
}