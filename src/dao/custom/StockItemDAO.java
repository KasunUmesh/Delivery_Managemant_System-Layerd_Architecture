package dao.custom;

import dao.CrudDAO;
import entity.StockItem;

import java.sql.SQLException;

public interface StockItemDAO extends CrudDAO<StockItem, String> {
    String generateNewID() throws SQLException, ClassNotFoundException;
}
