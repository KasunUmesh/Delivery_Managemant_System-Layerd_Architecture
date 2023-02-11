package dto;

import java.io.Serializable;

public class CustomDTO implements Serializable {
    private String orderNumber;
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private int itemQTY;

    public CustomDTO() {
    }

    public CustomDTO(String orderNumber, String itemCode, String itemName, String itemDescription, int itemQTY) {
        this.orderNumber = orderNumber;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQTY = itemQTY;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemQTY() {
        return itemQTY;
    }

    public void setItemQTY(int itemQTY) {
        this.itemQTY = itemQTY;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemQTY=" + itemQTY +
                '}';
    }
}
