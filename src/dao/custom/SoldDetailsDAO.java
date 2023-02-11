package dao.custom;

import dao.CrudDAO;
import entity.SoldDetails;

import java.sql.SQLException;

public interface SoldDetailsDAO extends CrudDAO<SoldDetails, String> {
    boolean ifSoldExist(String oid) throws SQLException, ClassNotFoundException;
}
