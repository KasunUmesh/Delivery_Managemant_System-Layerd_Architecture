package bo.custom;

import bo.SuperBO;
import dto.StockItemDTO;
import javafx.collections.ObservableList;
import view.tdm.StockItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockItemBO extends SuperBO {
    ArrayList<StockItemDTO> getAllStockItem() throws SQLException, ClassNotFoundException;
    boolean addItem(StockItemDTO stockItemDTO) throws SQLException, ClassNotFoundException;
    boolean updateItem(StockItemDTO stockItemDTO) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException;
    String generateNewItemCode() throws SQLException, ClassNotFoundException;
    ObservableList<StockItemTM> searchStockItem(String s) throws SQLException, ClassNotFoundException;
}
