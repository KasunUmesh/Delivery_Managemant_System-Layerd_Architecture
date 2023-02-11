package bo.custom;

import bo.SuperBO;
import dto.CompanyOrderDTO;
import dto.CustomDTO;
import dto.PlaceOrderDTO;
import dto.StockItemDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ConfirmOrderBO extends SuperBO {
    boolean ConfirmOrder(CompanyOrderDTO dto) throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    ArrayList<StockItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    StockItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;
    ArrayList<CompanyOrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;
    ArrayList<CustomDTO> getAllOrderDetails(String orderNumber) throws SQLException, ClassNotFoundException;

}
