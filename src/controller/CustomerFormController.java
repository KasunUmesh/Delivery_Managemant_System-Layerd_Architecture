package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import view.tdm.CustomerTM;

import java.sql.SQLException;

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
            txtCustomerID.clear();
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
        /*String customerID = txtCustomerID.getText();
        String customerName = txtCustomerName.getText();
        String shopName = txtShopName.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContactNumber.getText();

        try {
            CustomerDTO customerDTO = new CustomerDTO(customerID, customerName, shopName, address, contactNumber);
            customerBO.updateCustomer(customerDTO);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {

    }

    public void txtSearchCustomerOnKey(KeyEvent keyEvent) {
    }
}
