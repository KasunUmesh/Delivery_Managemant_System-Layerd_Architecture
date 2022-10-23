package controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SoldItemFormController {

    public JFXComboBox cmbCustomerID;
    public JFXTextField txtCustomerName;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtSoldDate;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtSoldItemQty;
    public JFXButton btnViewSoldItem;
    public JFXButton btnViewAddItem;
    public JFXComboBox cmbVehicleId;
    public Pane pnSoldItem;
    public TableView tblSoldItem;
    public TableColumn colSoldCustomerID;
    public TableColumn colVehicleID;
    public TableColumn colSoldDate;
    public TableColumn colSoldTotalQTY;
    public TableColumn colSoldTotalPrice;

    public TableColumn colSoldItemCode;

    public Pane pnAddSoldItem;
    public TableView tblAddSoldItem;
    public TableColumn colCustomerID;
    public TableColumn colSoldListVehicleID;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colSoldListDate;
    public TableColumn colItemQty;
    public TableColumn colTotalPrice;
    public Label lblTotalCost;

    public void initialize(){

        loadDate();

        pnAddSoldItem.setVisible(true);
        btnViewSoldItem.setVisible(true);
        btnViewAddItem.setVisible(false);
        pnSoldItem.setVisible(false);
    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtSoldDate.setText(format.format(date));
    }

    public void btnViewSoldItemOnAction(ActionEvent actionEvent) {

        pnSoldItem.setVisible(true);
        new ZoomIn(pnSoldItem).play();
        btnViewAddItem.setVisible(true);

        pnAddSoldItem.setVisible(false);
        btnViewSoldItem.setVisible(false);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnViewAddItemOnAction(ActionEvent actionEvent) {
        pnAddSoldItem.setVisible(true);
        new ZoomIn(pnAddSoldItem).play();
        btnViewSoldItem.setVisible(true);

        pnSoldItem.setVisible(false);
        btnViewAddItem.setVisible(false);
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveAddItemOnAction(ActionEvent actionEvent) {
    }
}
