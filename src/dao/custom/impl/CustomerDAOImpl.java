package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO CustomerShop (customerID, customerName, shopName, address, contactNumber) VALUES (?,?,?,?,?)", dto.getCustomerID(), dto.getCustomerName(), dto.getShopName(), dto.getAddress(), dto.getContactNumber());
    }

    @Override
    public boolean delete(String customerID) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM CustomerShop WHERE customerID=?", customerID);
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE CustomerShop SET customerName=?, shopName=?, address=?, contactNumber=? WHERE customerID=?", dto.getCustomerName(), dto.getShopName(), dto.getAddress(), dto.getContactNumber(), dto.getCustomerID());
    }

    @Override
    public CustomerDTO search(String s) {
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM CustomerShop");
        while (rst.next()) {
            allCustomers.add(new CustomerDTO(rst.getString("customerID"), rst.getString("customerName"), rst.getString("shopName"), rst.getString("address"), rst.getString("contactNumber")));
        }
        return allCustomers;
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT customerID FROM CustomerShop ORDER BY customerID DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("customerID");
            int newCustomerID = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C%03d", newCustomerID);
        } else {
            return "C001";
        }

    }
}
