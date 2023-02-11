package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class SoldDetailsDTO implements Serializable {
    private String customerID;
    private LocalDate soldDate;
    private String itemCode;
    private int soldItemQTY;
    private double soldTotalPrice;
    private String vehicleID;

    public SoldDetailsDTO() {
    }

    public SoldDetailsDTO(String customerID, LocalDate soldDate, String itemCode, int soldItemQTY, double soldTotalPrice, String vehicleID) {
        this.customerID = customerID;
        this.soldDate = soldDate;
        this.itemCode = itemCode;
        this.soldItemQTY = soldItemQTY;
        this.soldTotalPrice = soldTotalPrice;
        this.vehicleID = vehicleID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDate getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(LocalDate soldDate) {
        this.soldDate = soldDate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    @Override
    public String toString() {
        return "SoldDetailsDTO{" +
                "customerID='" + customerID + '\'' +
                ", soldDate=" + soldDate +
                ", itemCode='" + itemCode + '\'' +
                ", soldItemQTY=" + soldItemQTY +
                ", soldTotalPrice=" + soldTotalPrice +
                ", vehicleID='" + vehicleID + '\'' +
                '}';
    }
}
