package dao.custom;

import dao.CrudDAO;
import entity.Vehicle;

import java.sql.SQLException;

public interface VehicleDAO extends CrudDAO<Vehicle, String> {
    String generateNewID() throws SQLException, ClassNotFoundException;
}
