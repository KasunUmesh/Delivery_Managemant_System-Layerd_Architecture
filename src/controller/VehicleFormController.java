package controller;

import bo.BoFactory;
import bo.custom.VehicleBO;
import com.jfoenix.controls.JFXTextField;
import dto.VehicleDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import view.tdm.VehicleTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleFormController {
    public JFXTextField txtVehicleID;
    public JFXTextField txtVehicleNumber;
    public JFXTextField txtVehicleType;
    public JFXTextField txtDescription;
    public TableView<VehicleTM> tblVehicle;
    public TableColumn colVehicleID;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDescription;
    public JFXTextField txtSearchVehicle;

    private final VehicleBO vehicleBO = (VehicleBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.VEHICLE);

    public void initialize() {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadAllVehicles();
        txtVehicleID.setText(generateNewID());
        txtVehicleID.setEditable(false);
    }

    private void loadAllVehicles() {
        tblVehicle.getItems().clear();

        try {
            ArrayList<VehicleDTO> allVehicle = vehicleBO.getAllVehicle();
            for (VehicleDTO vehicle : allVehicle) {
                tblVehicle.getItems().add(new VehicleTM(vehicle.getVehicleID(), vehicle.getVehicleNumber(), vehicle.getVehicleType(), vehicle.getDescription()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnAddVehicleOnAction(ActionEvent actionEvent) {
        String vehicleID = txtVehicleID.getText();
        String vehicleNumber = txtVehicleNumber.getText();
        String vehicleType = txtVehicleType.getText();
        String desc = txtDescription.getText();


        try {
            VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleNumber, vehicleType, desc);
            vehicleBO.addVehicle(vehicleDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();
            txtVehicleID.setText(generateNewID());
            loadAllVehicles();

            txtVehicleNumber.clear();
            txtVehicleType.clear();
            txtDescription.clear();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Save the Vehicle" + e.getMessage()).show();
            txtVehicleNumber.clear();
            txtVehicleType.clear();
            txtDescription.clear();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String vehicleID = txtVehicleID.getText();
        String vehicleNumber = txtVehicleNumber.getText();
        String vehicleType = txtVehicleType.getText();
        String desc = txtDescription.getText();


        try {
            VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleNumber, vehicleType, desc);
            vehicleBO.updateVehicle(vehicleDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Success..").show();
            txtVehicleID.setText(generateNewID());
            loadAllVehicles();

            txtVehicleNumber.clear();
            txtVehicleType.clear();
            txtDescription.clear();
            
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the Vehicle " + vehicleID + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        VehicleTM selectedVehicle = tblVehicle.getSelectionModel().getSelectedItem();
        txtVehicleID.setText(selectedVehicle.getVehicleID());
        txtVehicleNumber.setText(selectedVehicle.getVehicleNumber());
        txtVehicleType.setText(selectedVehicle.getVehicleType());
        txtDescription.setText(selectedVehicle.getDescription());
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchVehicleOnKey(KeyEvent keyEvent) {
    }

    private String generateNewID(){
        try {
            return vehicleBO.generateNewVehicleID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblVehicle.getItems().isEmpty()) {
            return "V001";
        }else {
            String id = getLastVehicleID();
            int newVehicleID = Integer.parseInt(id.replace("V", "")) +1;
            return String.format("V%03d", newVehicleID);
        }
    }

    private String getLastVehicleID() {
        List<VehicleTM> tempVehicleList = new ArrayList<>(tblVehicle.getItems());
        Collections.sort(tempVehicleList);
        return tempVehicleList.get(tempVehicleList.size() -1).getVehicleID();
    }
}
