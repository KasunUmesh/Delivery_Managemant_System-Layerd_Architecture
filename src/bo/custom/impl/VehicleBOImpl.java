package bo.custom.impl;

import bo.custom.VehicleBO;
import dao.DAOFactory;
import dao.custom.VehicleDAO;
import dto.VehicleDTO;
import entity.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tdm.VehicleTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {

    private VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleDTO> allVehicle = new ArrayList<>();
        ArrayList<Vehicle> all = vehicleDAO.getAll();
        for (Vehicle vehicle : all) {
            allVehicle.add(new VehicleDTO(vehicle.getVehicleID(), vehicle.getVehicleNumber(), vehicle.getVehicleType(), vehicle.getDescription()));
        }
        return allVehicle;
    }

    @Override
    public boolean addVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.add(new Vehicle(dto.getVehicleID(), dto.getVehicleNumber(), dto.getVehicleType(), dto.getDescription()));
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(dto.getVehicleID(), dto.getVehicleNumber(), dto.getVehicleType(), dto.getDescription()));
    }

    @Override
    public boolean deleteVehicle(String vehicleID) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(vehicleID);
    }

    @Override
    public String generateNewVehicleID() throws SQLException, ClassNotFoundException {
        return vehicleDAO.generateNewID();
    }

    @Override
    public ObservableList<VehicleTM> searchVehicle(String s) throws SQLException, ClassNotFoundException {
        ObservableList<VehicleTM> allVehicle = FXCollections.observableArrayList();
        ObservableList<Vehicle> vehicle = vehicleDAO.search(s);

        for (Vehicle v : vehicle) {
            allVehicle.add(new VehicleTM(v.getVehicleID(), v.getVehicleNumber(), v.getVehicleType(), v.getDescription()));
        }
        return allVehicle;
    }
}
