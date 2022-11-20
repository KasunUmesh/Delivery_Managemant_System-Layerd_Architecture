package controller;

import animatefx.animation.ZoomIn;
import bo.BoFactory;
import bo.custom.EmployeeBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.EmployeeDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import view.tdm.EmployeeTM;

import java.sql.SQLException;

public class EmployeeFormController {
    public JFXTextField txtEmployeeID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNumber;
    public JFXTextField txtPosition;
    public JFXTextField txtEmployeeName;
    public JFXComboBox cmbEmployeeID;
    public JFXTextField txtAttendDate;
    public JFXComboBox cmbAttend;
    public Pane pnViewAttend;
    public JFXTextField txtSearchAttend;
    public TableView tblEmployeeAttend;
    public TableColumn colEmployeeIDAttend;
    public TableColumn colNameAttend;
    public TableColumn colDateAttend;
    public TableColumn colAttendance;
    public Pane pnViewEmployee;
    public TableView<EmployeeTM> tblEmployee;
    public TableColumn colEmployeeID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNumber;
    public TableColumn colPosition;
    public JFXTextField txtSearchEmployee;

    private final EmployeeBO employeeBO = (EmployeeBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.EMPLOYEE);

    public void initialize() {

    }

    public void btnViewEmployeeOnAction(ActionEvent actionEvent) {
        pnViewEmployee.setVisible(true);
        new ZoomIn(pnViewEmployee).play();
        pnViewAttend.setVisible(false);
    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {
        String employeeID = txtEmployeeID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContactNumber.getText();
        String position = txtPosition.getText();


        try {
            EmployeeDTO employeeDTO = new EmployeeDTO(employeeID, name, address, contactNumber, position);
            employeeBO.addEmployee(employeeDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();

            txtEmployeeID.clear();
            txtName.clear();
            txtAddress.clear();
            txtContactNumber.clear();
            txtPosition.clear();

        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, "Failed to Save the Employee" + e.getMessage()).show();
            txtEmployeeID.clear();
            txtName.clear();
            txtAddress.clear();
            txtContactNumber.clear();
            txtPosition.clear();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddAttendOnAction(ActionEvent actionEvent) {
    }

    public void btnViewAttendOnAction(ActionEvent actionEvent) {
        pnViewAttend.setVisible(true);
        new ZoomIn(pnViewAttend).play();
        pnViewEmployee.setVisible(false);
    }

    public void txtSearchAttendOnKey(KeyEvent keyEvent) {
    }

    public void btnRemoveAttendOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchEmployeeOnKeyRelease(KeyEvent keyEvent) {
    }
}
