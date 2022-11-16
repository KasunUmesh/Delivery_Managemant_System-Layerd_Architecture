package entity;


import java.time.LocalDate;

public class CompanyOrder {
    private String orderNumber;
    private LocalDate orderDate;
    private int orderItemQTY;

    public CompanyOrder() {
    }

    public CompanyOrder(String orderNumber, LocalDate orderDate, int orderItemQTY) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderItemQTY = orderItemQTY;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderItemQTY() {
        return orderItemQTY;
    }

    public void setOrderItemQTY(int orderItemQTY) {
        this.orderItemQTY = orderItemQTY;
    }
}
