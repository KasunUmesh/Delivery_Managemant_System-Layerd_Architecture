package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SoldDetailsDAO;
import entity.SoldDetails;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SoldDetailsDAOImpl implements SoldDetailsDAO {
    @Override
    public boolean add(SoldDetails dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `SoldDetails` (customerID, soldDate, itemCode, soldItemQTY, soldTotalPrice, vehicleID) VALUES (?,?,?,?,?,?)", dto.getCustomerID(), dto.getSoldDate(), dto.getItemCode(), dto.getSoldItemQTY(), dto.getSoldTotalPrice(), dto.getVehicleID());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(SoldDetails soldDetails) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<SoldDetails> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SoldDetails> allSold = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM SoldDetails");
        while (rst.next()) {
            allSold.add(new SoldDetails(rst.getString("customerID"), LocalDate.parse( rst.getString("soldDate")), rst.getString("itemCode"), rst.getInt("soldItemQTY"), rst.getDouble("soldTotalPrice"), rst.getString("vehicleID")));
        }
        return allSold;
    }

    @Override
    public ObservableList<SoldDetails> search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean ifSoldExist(String oid) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }
}
