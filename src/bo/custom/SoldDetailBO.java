package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.SoldDetailsDTO;
import dto.StockItemDTO;
import dto.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SoldDetailBO extends SuperBO {
    boolean ConfirmSold(SoldDetailsDTO dto) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    ArrayList<StockItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException;
    StockItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;
    CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException;
}
