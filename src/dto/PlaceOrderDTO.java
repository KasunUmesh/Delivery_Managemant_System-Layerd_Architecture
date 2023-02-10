package dto;

import java.io.Serializable;

public class PlaceOrderDTO implements Serializable {

    private String oNumber;
    private String itemCode;
    private int itemQTY;

    public PlaceOrderDTO() {
    }

    public PlaceOrderDTO(String orderNumber, String itemCode, int itemQTY) {
        this.oNumber = orderNumber;
        this.itemCode = itemCode;
        this.itemQTY = itemQTY;
    }

    public String getOrderNumber() {
        return oNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.oNumber = orderNumber;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getItemQTY() {
        return itemQTY;
    }

    public void setItemQTY(int itemQTY) {
        this.itemQTY = itemQTY;
    }

    @Override
    public String toString() {
        return "PlaceOrderDTO{" +
                "orderNumber='" + oNumber + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemQTY=" + itemQTY +
                '}';
    }
}
