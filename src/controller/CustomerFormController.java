package controller;

import bo.CustomerBO;
import bo.CustomerBOImpl;
import com.jfoenix.controls.JFXTextField;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
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

    private CustomerBO customerBO = new CustomerBOImpl();

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        String customerID = txtCustomerID.getText();
        String customerName = txtCustomerName.getText();
        String shopName = txtShopName.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContactNumber.getText();

        System.out.println(customerID);
        System.out.println(customerName);
        System.out.println(shopName);
        System.out.println(address);
        System.out.println(contactNumber);

       /* try {
            CustomerDTO customerDTO = new CustomerDTO(customerID, customerName, shopName, address, contactNumber);
            customerDAO.addCustomer(customerDTO);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/

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
