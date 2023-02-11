package view.tdm;

import java.time.LocalDate;

public class SoldItemDetailsTM {
    private String customerID;
    private String vehicleID;
    private String itemCode;
    private LocalDate soldDate;
    private int soldItemQTY;
    private double soldTotalPrice;

    public SoldItemDetailsTM() {
    }

    public SoldItemDetailsTM(String customerID, String vehicleID, String itemCode, LocalDate soldDate, int soldItemQTY, double soldTotalPrice) {
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.itemCode = itemCode;
        this.soldDate = soldDate;
        this.soldItemQTY = soldItemQTY;
        this.soldTotalPrice = soldTotalPrice;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public LocalDate getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(LocalDate soldDate) {
        this.soldDate = soldDate;
    }

    public int getSoldItemQTY() {
        return soldItemQTY;
    }

    public void setSoldItemQTY(int soldItemQTY) {
        this.soldItemQTY = soldItemQTY;
    }

    public double getSoldTotalPrice() {
        return soldTotalPrice;
    }

    public void setSoldTotalPrice(double soldTotalPrice) {
        this.soldTotalPrice = soldTotalPrice;
    }

    @Override
    public String toString() {
        return "SoldItemDetailsTM{" +
                "customerID='" + customerID + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", soldDate=" + soldDate +
                ", soldItemQTY=" + soldItemQTY +
                ", soldTotalPrice=" + soldTotalPrice +
                '}';
    }
}
