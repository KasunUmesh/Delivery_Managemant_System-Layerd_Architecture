package dto;

import java.io.Serializable;

public class CustomDTO implements Serializable {

    private String itemCode;
    private String itemName;
    private String itemDescription;
    private int itemQTY;

    public CustomDTO() {
    }

    public CustomDTO(String itemCode, String itemName, String itemDescription, int itemQTY) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQTY = itemQTY;
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
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemQTY=" + itemQTY +
                '}';
    }
}
