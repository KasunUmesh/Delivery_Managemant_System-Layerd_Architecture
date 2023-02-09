package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CompanyOrderDTO implements Serializable {

    private String orderNumber;
    private LocalDate orderDate;
    private int orderItemQTY;

    public CompanyOrderDTO() {
    }

    public CompanyOrderDTO(String orderNumber, LocalDate orderDate, int orderItemQTY) {
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

    @Override
    public String toString() {
        return "CompanyOrderDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderDate=" + orderDate +
                ", orderItemQTY=" + orderItemQTY +
                '}';
    }
}
