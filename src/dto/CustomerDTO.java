package dto;

import entity.Customers;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

    private String customerID;
    private String customerName;
    private String shopName;
    private String address;
    private String contactNumber;

    public CustomerDTO(Customers customers, Customers customers1, Customers customers2, Customers customers3, Customers customers4) {
    }

    public CustomerDTO(String customerID, String customerName, String shopName, String address, String contactNumber) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.shopName = shopName;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", shopName='" + shopName + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
