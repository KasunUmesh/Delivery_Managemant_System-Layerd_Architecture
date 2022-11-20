package view.tdm;

import entity.Customers;

public class CustomerTM implements Comparable<CustomerTM>{

    private String customerID;
    private String customerName;
    private String shopName;
    private String address;
    private String contactNumber;

    public CustomerTM(Customers customers, Customers customers1, Customers customers2, Customers customers3, Customers customers4) {
    }

    public CustomerTM(String customerID, String customerName, String shopName, String address, String contactNumber) {
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
        return "CustomerTM{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", shopName='" + shopName + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(CustomerTM o) {

        return customerID.compareTo(o.getCustomerID());
    }
}
