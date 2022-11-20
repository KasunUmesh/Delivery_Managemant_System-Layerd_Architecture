package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerFormController {
    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerName;
    public JFXTextField txtShopName;
    public JFXTextField txtContactNumber;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colShopName;
    public TableColumn colAddress;
    public TableColumn colContactNumber;
    public JFXTextField txtAddress;
    public JFXTextField txtSearchCustomer;

    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.CUSTOMER);

    public void initialize(){
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        loadAllCustomers();
        txtCustomerID.setText(generateNewID());
        txtCustomerID.setEditable(false);


    }

    private void loadAllCustomers(){
        tblCustomer.getItems().clear();

        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomer();
            for (CustomerDTO customer : allCustomers){
                tblCustomer.getItems().add(new CustomerTM(customer.getCustomerID(), customer.getCustomerName(), customer.getShopName(), customer.getAddress(), customer.getContactNumber()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        String customerID = txtCustomerID.getText();
        String customerName = txtCustomerName.getText();
        String shopName = txtShopName.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContactNumber.getText();


        try {
            CustomerDTO customerDTO = new CustomerDTO(customerID, customerName, shopName, address, contactNumber);
            customerBO.addCustomer(customerDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();
            txtCustomerID.setText(generateNewID());
            loadAllCustomers();


            txtCustomerName.clear();
            txtShopName.clear();
            txtAddress.clear();
            txtContactNumber.clear();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Save the Customer" + e.getMessage()).show();
            txtCustomerID.clear();
            txtCustomerName.clear();
            txtShopName.clear();
            txtAddress.clear();
            txtContactNumber.clear();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String customerID = txtCustomerID.getText();
        String customerName = txtCustomerName.getText();
        String shopName = txtShopName.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContactNumber.getText();


        try {
            CustomerDTO customerDTO = new CustomerDTO(customerID, customerName, shopName, address, contactNumber);
            customerBO.updateCustomer(customerDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Success..").show();
            txtCustomerID.setText(generateNewID());
            loadAllCustomers();


            txtCustomerName.clear();
            txtShopName.clear();
            txtAddress.clear();
            txtContactNumber.clear();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + customerID + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnEditOnAction(ActionEvent actionEvent){

        CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        txtCustomerID.setText(selectedCustomer.getCustomerID());
        txtCustomerName.setText(selectedCustomer.getCustomerName());
        txtShopName.setText(selectedCustomer.getShopName());
        txtAddress.setText(selectedCustomer.getAddress());
        txtContactNumber.setText(selectedCustomer.getContactNumber());
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        String id = tblCustomer.getSelectionModel().getSelectedItem().getCustomerID();

        try {

            customerBO.deleteCustomer(id);
            new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();
            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();
            txtCustomerID.setText(generateNewID());


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void txtSearchCustomerOnKey(KeyEvent keyEvent) {
    }

    private String generateNewID(){
        try {
            return customerBO.generateNewCustomerID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblCustomer.getItems().isEmpty()){
            return "C001";
        }else {
            String id = getLastCustomerID();
            int newCustomerID = Integer.parseInt(id.replace("C", "")) +1;
            return String.format("C%03d", newCustomerID);
        }
    }

    private String getLastCustomerID(){
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomer.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getCustomerID();
    }
}
