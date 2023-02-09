package dao.custom;

import dao.CrudDAO;
import entity.CompanyOrder;

import java.sql.SQLException;

public interface CompanyOrderDAO extends CrudDAO<CompanyOrder, String> {
    boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
}
