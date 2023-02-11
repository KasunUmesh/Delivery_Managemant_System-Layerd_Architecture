package view.tdm;

import java.time.LocalDate;

public class OrderTM {

    private String orderNumber;
    private LocalDate orderDate;
    private int orderItemQty;

    public OrderTM() {
    }

    public OrderTM(String orderNumber, LocalDate orderDate, int orderItemQty) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderItemQty = orderItemQty;
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

    public int getOrderItemQty() {
        return orderItemQty;
    }

    public void setOrderItemQty(int orderItemQty) {
        this.orderItemQty = orderItemQty;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderItemQty=" + orderItemQty +
                '}';
    }
}
