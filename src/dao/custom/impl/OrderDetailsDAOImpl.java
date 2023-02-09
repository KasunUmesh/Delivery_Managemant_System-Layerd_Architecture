package dao.custom.impl;

import dao.custom.OrderDetailsDAO;
import entity.PlaceOrder;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<PlaceOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<PlaceOrder> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
