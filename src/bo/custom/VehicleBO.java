package bo.custom;

import bo.SuperBO;
import dto.VehicleDTO;
import javafx.collections.ObservableList;
import view.tdm.VehicleTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO extends SuperBO {
    ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException;
    boolean addVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;
    boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;
    boolean deleteVehicle(String vehicleID) throws SQLException, ClassNotFoundException;
    String generateNewVehicleID() throws SQLException, ClassNotFoundException;
    ObservableList<VehicleTM> searchVehicle(String s) throws SQLException, ClassNotFoundException;
}
