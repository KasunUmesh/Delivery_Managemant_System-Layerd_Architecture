package controller;

import animatefx.animation.ZoomIn;
import bo.BoFactory;
import bo.custom.ConfirmOrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import view.tdm.NewOrderItemListTM;
import view.tdm.OrderItemListTM;
import view.tdm.OrderTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemOrderFormController {
    public JFXTextField txtItemDescription;
    public JFXTextField txtOrderNumber;
    public JFXTextField txtItemName;
    public JFXTextField txtItemQty;
    public JFXComboBox<String> cmbItemCode;
    public JFXButton btnViewOrderDetail;
    public JFXButton btnViewAddItem;
    public Label lblDate;
    public Pane pnOrderDetails;
    public TableView<OrderTM> tblOrder;
    public TableColumn colOrderNumberOrdertb;
    public TableColumn colOrderDate;
    public TableColumn colOrderItemQty;
    public Pane pnOrderItemList;
    public TableView<OrderItemListTM> tblOrderItemList;
    public TableColumn colOrderItemCode;
    public TableColumn colOrderItemName;
    public TableColumn colOrderItemDescription;
    public TableColumn colOrderDetailItemQty;
    public Pane pnAddItemList;
    public TableView<NewOrderItemListTM> tblItemList;
    public TableColumn colOrderNumber;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colItemQty;
    private final ConfirmOrderBO confirmOrderBO = (ConfirmOrderBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.CONFIRM_ORDER);

    public void initialize(){

        loadDate();
        txtOrderNumber.setText(generateNewOrderID());

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });

        pnAddItemList.setVisible(true);
        btnViewOrderDetail.setVisible(true);
        btnViewAddItem.setVisible(false);
        pnOrderDetails.setVisible(false);
        pnOrderItemList.setVisible(false);
    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));
    }

    public  String generateNewOrderID() {
        try {
            return confirmOrderBO.generateNewOrderId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnViewOrderDetailOnAction(ActionEvent actionEvent) {
        pnOrderDetails.setVisible(true);
        new ZoomIn(pnOrderDetails).play();
        pnOrderItemList.setVisible(true);
        new ZoomIn(pnOrderItemList).play();
        btnViewAddItem.setVisible(true);

        pnAddItemList.setVisible(false);
        btnViewOrderDetail.setVisible(false);
    }

    public void btnViewAddItemOnAction(ActionEvent actionEvent) {

        pnAddItemList.setVisible(true);
        new ZoomIn(pnAddItemList).play();
        btnViewOrderDetail.setVisible(true);

        pnOrderDetails.setVisible(false);
        pnOrderItemList.setVisible(false);
        btnViewAddItem.setVisible(false);
    }

    public void btnRemoveAddItemOnAction(ActionEvent actionEvent) {
    }

    public void btnConfirmOrderOnAction(ActionEvent actionEvent) {
    }
}
