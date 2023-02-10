package dao.custom;

import dao.CrudDAO;
import dto.StockItemDTO;
import entity.StockItem;

import java.sql.SQLException;

public interface StockItemDAO extends CrudDAO<StockItem, String> {
    String generateNewID() throws SQLException, ClassNotFoundException;
    StockItem getItems(String itemCode) throws SQLException, ClassNotFoundException;
}
