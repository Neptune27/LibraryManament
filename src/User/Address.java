package User;

import General.Input.NString;

import java.io.Serializable;
import java.util.Arrays;

public class Address implements Serializable {
    //Attribute
    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    //Constructor
    public Address(String houseNumber, String street, String ward, String district, String city) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public Address() {
        houseNumber = "";
        street = "";
        ward = "";
        district = "";
        city = "";
    }

    public Address setFromInput() {
        houseNumber = new NString("số nhà").getFromInput().getValue();
        street = new NString("đường").getFromInput().getValue();
        ward = new NString("phường").getFromInput().getValue();
        district = new NString("quận").getFromInput().getValue();
        city = new NString("thành phố").getFromInput().getValue();
        return this;
    }
    public Address(String s){
        var a_s = Arrays.stream(s.split(",")).toList();
        houseNumber = a_s.get(0);
        street = a_s.get(1);
        ward = a_s.get(2);
        district = a_s.get(3);
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
        return houseNumber +", "+street+", "+ward+", "+district;
    }
}
