package controller;

import bo.BoFactory;
import bo.custom.VehicleBO;
import com.jfoenix.controls.JFXTextField;
import dto.VehicleDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import view.tdm.VehicleTM;

import java.sql.SQLException;

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

    public void btnAddVehicleOnAction(ActionEvent actionEvent) {
        String vehicleID = txtVehicleID.getText();
        String vehicleNumber = txtVehicleNumber.getText();
        String vehicleType = txtVehicleType.getText();
        String desc = txtDescription.getText();


        try {
            VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleNumber, vehicleType, desc);
            vehicleBO.addVehicle(vehicleDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();

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
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchVehicleOnKey(KeyEvent keyEvent) {
    }
}
