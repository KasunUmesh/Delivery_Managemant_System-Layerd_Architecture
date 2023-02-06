package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.VehicleDAO;
import entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public boolean add(Vehicle dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Vehicle (vehicleID, vehicleNumber, vehicleType, description) VALUES (?,?,?,?)", dto.getVehicleID(), dto.getVehicleNumber(), dto.getVehicleType(), dto.getDescription());
    }

    @Override
    public boolean delete(String vehicleID) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Vehicle WHERE vehicleID=?", vehicleID);
    }

    @Override
    public boolean update(Vehicle dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Vehicle SET vehicleNumber=?, vehicleType=?, description=? WHERE vehicleID=?", dto.getVehicleNumber(), dto.getVehicleType(), dto.getDescription(), dto.getVehicleID());
    }

    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> allVehicle = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Vehicle");
        while (rst.next()) {
            allVehicle.add(new Vehicle(rst.getString("vehicleID"), rst.getString("vehicleNumber"), rst.getString("vehicleType"), rst.getString("description")));
        }
        return allVehicle;
    }

    @Override
    public ObservableList<Vehicle> search(String value) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Vehicle WHERE CONCAT(vehicleID, vehicleNumber, vehicleType) LIKE '%"+value+"%'");
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        while (rst.next()) {
            vehicles.add(new Vehicle(rst.getString("vehicleID"), rst.getString("vehicleNumber"), rst.getString("vehicleType"), rst.getString("description")));
        }
        return vehicles;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT vehicleID FROM Vehicle ORDER BY vehicleID DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("vehicleID");
            int newVehicleID = Integer.parseInt(id.replace("V", "")) + 1;
            return String.format("V%03d", newVehicleID);
        }else {
            return "V001";
        }
    }
}
