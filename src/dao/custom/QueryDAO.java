package dao.custom;

import dao.SuperDAO;
import dto.CustomDTO;
import dto.PlaceOrderDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomDTO> getOrderDetailsFromOrderID(String oid) throws SQLException, ClassNotFoundException;
}
