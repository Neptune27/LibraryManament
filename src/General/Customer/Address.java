package General.Customer;

import java.util.Scanner;

public class Address {
    //Attribute
    Scanner sc = new Scanner(System.in);
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

}

