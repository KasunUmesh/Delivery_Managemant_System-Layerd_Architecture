package controller;

import animatefx.animation.ZoomIn;
import bo.BoFactory;
import bo.custom.SoldDetailBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dto.StockItemDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import view.tdm.SoldItemAddTM;
import view.tdm.SoldItemDetailsTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SoldItemFormController {

    public JFXComboBox<String> cmbCustomerID;
    public JFXTextField txtCustomerName;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtSoldDate;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtSoldItemQty;
    public JFXButton btnViewSoldItem;
    public JFXButton btnViewAddItem;
    public JFXComboBox<String> cmbVehicleId;
    public Pane pnSoldItem;
    public TableView<SoldItemDetailsTM> tblSoldItem;
    public TableColumn colSoldCustomerID;
    public TableColumn colVehicleID;
    public TableColumn colSoldDate;
    public TableColumn colSoldTotalQTY;
    public TableColumn colSoldTotalPrice;
    public TableColumn colSoldItemCode;

    public Pane pnAddSoldItem;
    public TableView<SoldItemAddTM> tblAddSoldItem;
    public TableColumn colCustomerID;
    public TableColumn colSoldListVehicleID;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colSoldListDate;
    public TableColumn colItemQty;
    public TableColumn colTotalPrice;
    public Label lblTotalCost;

    private final SoldDetailBO soldDetailBO = (SoldDetailBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.SOLD_DETAILS);

    public void initialize(){
        colSoldCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        colSoldDate.setCellValueFactory(new PropertyValueFactory<>("soldDate"));
        colSoldTotalQTY.setCellValueFactory(new PropertyValueFactory<>("soldItemQTY"));
        colSoldTotalPrice.setCellValueFactory(new PropertyValueFactory<>("soldTotalPrice"));
        colSoldItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colSoldListVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colSoldListDate.setCellValueFactory(new PropertyValueFactory<>("soldDate"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("soldItemQTY"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("soldTotalPrice"));

        loadDate();
        loadAllItemCodes();

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

    private void loadAllItemCodes() {
        try {
            ArrayList<StockItemDTO> all = soldDetailBO.getAllItems();
            for (StockItemDTO dto : all) {
                cmbItemCode.getItems().add(dto.getItemCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
