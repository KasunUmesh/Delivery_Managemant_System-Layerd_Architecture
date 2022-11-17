package dao.custom;

import dao.CrudDAO;
import dto.CustomerDTO;
import entity.Customers;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customers, String> {
    String generateNewID() throws SQLException, ClassNotFoundException;
}
