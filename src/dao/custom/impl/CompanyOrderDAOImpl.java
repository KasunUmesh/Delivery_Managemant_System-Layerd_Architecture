package dao.custom.impl;

import dao.CrudDAO;
import dao.CrudUtil;
import dao.custom.CompanyOrderDAO;
import entity.CompanyOrder;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CompanyOrderDAOImpl implements CompanyOrderDAO {
    @Override
    public boolean add(CompanyOrder dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `CompanyOrder` (orderNumber, orderDate, orderItemQTY) VALUES (?,?,?)", dto.getOrderNumber(), dto.getOrderDate(), dto.getOrderItemQTY());
    }

    @Override
    public boolean delete(String orderNumber) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM CompanyOrder WHERE orderNumber=?", orderNumber);
    }

    @Override
    public boolean update(CompanyOrder companyOrder) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<CompanyOrder> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CompanyOrder> allOrder = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM CompanyOrder");
        while (rst.next()) {
            allOrder.add(new CompanyOrder(rst.getString("orderNumber"), LocalDate.parse(rst.getString("orderDate")), rst.getInt("orderItemQTY")));
        }
        return allOrder;
    }

    @Override
    public ObservableList<CompanyOrder> search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT orderNumber FROM `CompanyOrder` WHERE orderNumber=?", oid);
        return rst.next();
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT orderNumber FROM `CompanyOrder` ORDER BY orderNumber DESC LIMIT 1;");
        return rst.next() ? String.format("OD%03d", (Integer.parseInt(rst.getString("orderNumber").replace("OD", "")) + 1)) : "OD001";
    }
}
