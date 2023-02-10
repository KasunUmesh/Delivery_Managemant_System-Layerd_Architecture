package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CompanyOrderDTO implements Serializable {

    private String orderNumber;
    private LocalDate orderDate;
    private int orderItemQTY;
    private List<PlaceOrderDTO> orderDetail;

    public CompanyOrderDTO() {
    }

    public CompanyOrderDTO(String orderNumber, LocalDate orderDate, int orderItemQTY, List<PlaceOrderDTO> orderDetail) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderItemQTY = orderItemQTY;
        this.orderDetail = orderDetail;
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

    public List<PlaceOrderDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<PlaceOrderDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "CompanyOrderDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderDate=" + orderDate +
                ", orderItemQTY=" + orderItemQTY +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
