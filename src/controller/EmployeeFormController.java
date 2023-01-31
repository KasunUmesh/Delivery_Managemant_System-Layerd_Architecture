package controller;

import animatefx.animation.ZoomIn;
import bo.BoFactory;
import bo.custom.EmployeeAttendanceBO;
import bo.custom.EmployeeBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.EmployeeAttendanceDTO;
import dto.EmployeeDTO;
import entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import view.tdm.EmployeeAttendanceTM;
import view.tdm.EmployeeTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EmployeeFormController {
    private final EmployeeBO employeeBO = (EmployeeBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.EMPLOYEE);
    private final EmployeeAttendanceBO employeeAttendanceBO = (EmployeeAttendanceBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.EMPLOYEEATTENDANCE);
    public JFXTextField txtEmployeeID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNumber;
    public JFXTextField txtPosition;
    public JFXTextField txtEmployeeName;
    public JFXComboBox<String> cmbEmployeeID;
    public JFXTextField txtAttendDate;
    public JFXComboBox<String> cmbAttend;
    public Pane pnViewAttend;
    public JFXTextField txtSearchAttend;
    public TableView<EmployeeAttendanceTM> tblEmployeeAttend;
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

    public void initialize() {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));

        colEmployeeIDAttend.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colNameAttend.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colDateAttend.setCellValueFactory(new PropertyValueFactory<>("attendDate"));
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("attend"));

        loadAllEmployee();
        loadAllEmployeeAttend();
        txtEmployeeID.setText(generateNewID());
        txtEmployeeID.setEditable(false);
        loadAttendCombo();
        loadEmployeeID();

        cmbEmployeeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setEmployeeData(newValue);
        });

    }

    private void loadAttendCombo(){
        ObservableList<String> list = FXCollections.observableArrayList("Yes", "No");
        cmbAttend.setItems(list);
    }

    private void loadEmployeeID(){
        try {
            List<String> employeeID = employeeAttendanceBO.getEmployeeID();
            cmbEmployeeID.getItems().setAll(employeeID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setEmployeeData(String employeeID){
        try {
            Employee e1 = employeeAttendanceBO.getEmployee(employeeID);
            if (e1 == null){
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            }else {
                txtEmployeeName.setText(e1.getName());
                loadDate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtAttendDate.setText(format.format(date));
    }

    private void loadAllEmployee() {
        tblEmployee.getItems().clear();

        try {

            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for (EmployeeDTO e : allEmployee) {
                tblEmployee.getItems().add(new EmployeeTM(e.getEmployeeID(), e.getName(), e.getAddress(), e.getContactNumber(), e.getPosition()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllEmployeeAttend(){
        tblEmployeeAttend.getItems().clear();

        try {

            ArrayList<EmployeeAttendanceDTO> allAttend = employeeAttendanceBO.getAllEmployeeAttend();
            for (EmployeeAttendanceDTO e : allAttend) {
                tblEmployeeAttend.getItems().add(new EmployeeAttendanceTM(e.getEmployeeID(), e.getEmployeeName(), e.getAttendDate(), e.getAttend()));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
            txtEmployeeID.setText(generateNewID());
            loadAllEmployee();
            loadEmployeeID();


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
        String empAttendID = cmbEmployeeID.getValue();
        String empAttendName = txtEmployeeName.getText();
        LocalDate empAttendDate = LocalDate.parse(txtAttendDate.getText());
        String empAttend = cmbAttend.getValue();

        try {

            EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO(empAttendID, empAttendName, empAttendDate, empAttend);
            employeeAttendanceBO.addEmployeeAttend(employeeAttendanceDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();
            loadAllEmployeeAttend();
            txtEmployeeName.clear();
            txtAttendDate.clear();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Save the Employee Attendance" + e.getMessage()).show();

            cmbEmployeeID.setValue("Select");
            txtEmployeeName.clear();
            txtAttendDate.clear();
            cmbAttend.setValue("Select ");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnViewAttendOnAction(ActionEvent actionEvent) {
        pnViewAttend.setVisible(true);
        new ZoomIn(pnViewAttend).play();
        pnViewEmployee.setVisible(false);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String employeeID = txtEmployeeID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContactNumber.getText();
        String position = txtPosition.getText();


        try {
            EmployeeDTO employeeDTO = new EmployeeDTO(employeeID, name, address, contactNumber, position);
            employeeBO.updateEmployee(employeeDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Success..").show();
            txtEmployeeID.setText(generateNewID());
            loadAllEmployee();


            txtName.clear();
            txtAddress.clear();
            txtContactNumber.clear();
            txtPosition.clear();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the Employee " + employeeID + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        EmployeeTM selectedEmployee = tblEmployee.getSelectionModel().getSelectedItem();
        txtEmployeeID.setText(selectedEmployee.getEmployeeID());
        txtName.setText(selectedEmployee.getName());
        txtAddress.setText(selectedEmployee.getAddress());
        txtContactNumber.setText(selectedEmployee.getContactNumber());
        txtPosition.setText(selectedEmployee.getPosition());
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        String id = tblEmployee.getSelectionModel().getSelectedItem().getEmployeeID();

        try {

            employeeBO.deleteEmployee(id);
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Success..").show();
            tblEmployee.getItems().remove(tblEmployee.getSelectionModel().getSelectedItem());
            tblEmployee.getSelectionModel().clearSelection();
            txtEmployeeID.setText(generateNewID());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Employee " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtSearchEmployeeOnKeyRelease(KeyEvent keyEvent) {
        try {
            ObservableList<EmployeeTM> employee = employeeBO.searchEmployee(txtSearchEmployee.getText());
            tblEmployee.setItems(employee);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Employee Search Failed" + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRemoveAttendOnAction(ActionEvent actionEvent) {
        String id = tblEmployeeAttend.getSelectionModel().getSelectedItem().getEmployeeID();

        try {

            employeeAttendanceBO.deleteEmployeeAtted(id);
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Success..").show();
            tblEmployeeAttend.getItems().remove(tblEmployeeAttend.getSelectionModel().getSelectedItem());
            tblEmployeeAttend.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchAttendOnKey(KeyEvent keyEvent) {

        try {
            ObservableList<EmployeeAttendanceTM> empAttend = employeeAttendanceBO.searchEmployeeAttend(txtSearchAttend.getText());
            tblEmployeeAttend.setItems(empAttend);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Employee Search Failed" + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNewID(){
        try {
            return employeeBO.generateNewEmployeeID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblEmployee.getItems().isEmpty()){
            return "E001";
        }else {
            String id = getLastEmployeeID();
            int newEmployeeID = Integer.parseInt(id.replace("E", "")) + 1;
            return String.format("E%03d", newEmployeeID);
        }
    }

    private String getLastEmployeeID(){
        List<EmployeeTM> tempEmployeeList = new ArrayList<>(tblEmployee.getItems());
        Collections.sort(tempEmployeeList);
        return tempEmployeeList.get(tempEmployeeList.size() - 1).getEmployeeID();
    }
}
