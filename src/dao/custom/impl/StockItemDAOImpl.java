package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.StockItemDAO;
import dto.StockItemDTO;
import entity.StockItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockItemDAOImpl implements StockItemDAO {
    @Override
    public boolean add(StockItem dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO StockItem (itemCode, itemName, itemDescription, unitPrice, qtyOnStock) VALUES (?,?,?,?,?)", dto.getItemCode(), dto.getItemName(), dto.getItemDescription(),dto.getUnitPrice(), dto.getQtyOnStock());
    }

    @Override
    public boolean delete(String itemCode) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM StockItem WHERE itemCode=?", itemCode);
    }

    @Override
    public boolean update(StockItem dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE StockItem SET itemName=?, itemDescription=?, unitPrice=?, qtyOnStock=? WHERE itemCode=?", dto.getItemName(), dto.getItemDescription(), dto.getUnitPrice(), dto.getQtyOnStock(), dto.getItemCode());
    }

    @Override
    public ArrayList<StockItem> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<StockItem> allStockItem = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM StockItem");
        while (rst.next()) {
            allStockItem.add(new StockItem(rst.getString("itemCode"), rst.getString("itemName"), rst.getString("itemDescription"), rst.getDouble("unitPrice"), rst.getInt("qtyOnStock")));
        }
        return allStockItem;
    }

    @Override
    public ObservableList<StockItem> search(String value) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM StockItem WHERE CONCAT(itemCode, itemName, itemDescription) LIKE '%"+value+"%'");
        ObservableList<StockItem> stockItems = FXCollections.observableArrayList();
        while (rst.next()) {
            stockItems.add(new StockItem(rst.getString("itemCode"), rst.getString("itemName"), rst.getString("itemDescription"), rst.getDouble("unitPrice"), rst.getInt("qtyOnStock")));
        }
        return stockItems;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT itemCode FROM StockItem ORDER BY itemCode DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("itemCode");
            int newItemCode = Integer.parseInt(id.replace("I", "")) + 1;
            return String.format("I%03d", newItemCode);
        }else {
            return "I001";
        }
    }

    @Override
    public StockItem getItems(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM StockItem WHERE itemCode=?", itemCode);
        rst.next();
        return new StockItem(itemCode, rst.getString("itemName"), rst.getString("itemDescription"), rst.getDouble("unitPrice"), rst.getInt("qtyOnStock"));
    }
}
