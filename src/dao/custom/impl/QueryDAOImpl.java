package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import dto.CustomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    //    Create Join Query This Class
    @Override
    public ArrayList<CustomDTO> getOrderDetailsFromOrderID(String oid) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> allData = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT i.itemCode, i.itemName, i.itemDescription, p.itemQTY FROM StockItem i JOIN PlaceOrder p ON p.itemCode=i.itemCode WHERE p.orderNumber = '"+oid+"'");
        while (rst.next()) {
            allData.add(new CustomDTO(rst.getString("itemCode"), rst.getString("itemName"), rst.getString("itemDescription"), rst.getInt("itemQTY")));
        }
        return allData;
    }
}
