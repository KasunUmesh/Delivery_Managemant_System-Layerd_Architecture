package dao.custom;

import dao.CrudDAO;
import dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<CustomerDTO, String> {
    String generateNewID() throws SQLException, ClassNotFoundException;
}
