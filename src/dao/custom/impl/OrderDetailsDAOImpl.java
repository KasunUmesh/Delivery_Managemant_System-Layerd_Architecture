package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailsDAO;
import entity.PlaceOrder;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(PlaceOrder dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO PlaceOrder (orderNumber, itemCode, itemQTY) VALUES (?,?,?)", dto.getOrderNumber(), dto.getItemCode(), dto.getItemQTY());
    }

    @Override
    public boolean delete(String orderNumber) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM PlaceOrder WHERE orderNumber=?", orderNumber);
    }

    @Override
    public boolean update(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<PlaceOrder> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ObservableList<PlaceOrder> search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }
}
