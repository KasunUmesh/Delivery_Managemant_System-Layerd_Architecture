package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.SoldDetailsDTO;
import dto.StockItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SoldDetailBO extends SuperBO {
    boolean ConfirmSold(SoldDetailsDTO dto) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomers();
    ArrayList<StockItemDTO> getAllItems();
    StockItemDTO searchItem(String code);
    CustomerDTO searchCustomer(String s);
}
