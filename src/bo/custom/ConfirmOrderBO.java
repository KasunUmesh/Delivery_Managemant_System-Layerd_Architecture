package bo.custom;

import bo.SuperBO;
import dto.CompanyOrderDTO;
import dto.StockItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ConfirmOrderBO extends SuperBO {
    boolean ConfirmOrder(CompanyOrderDTO dto);
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    ArrayList<StockItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    StockItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

}
