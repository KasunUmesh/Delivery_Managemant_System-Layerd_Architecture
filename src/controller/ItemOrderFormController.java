package controller;

import animatefx.animation.ZoomIn;
import bo.BoFactory;
import bo.custom.ConfirmOrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.StockItemDTO;
import entity.StockItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import view.tdm.NewOrderItemListTM;
import view.tdm.OrderItemListTM;
import view.tdm.OrderTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    int orderAddItemRemove = -1;
    ObservableList<NewOrderItemListTM> obList = FXCollections.observableArrayList();

    public void initialize(){
        colOrderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));

        colOrderNumberOrdertb.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderItemQty.setCellValueFactory(new PropertyValueFactory<>("orderItemQty"));

        colOrderItemCode.setCellValueFactory(new PropertyValueFactory<>("orderItemCode"));
        colOrderItemName.setCellValueFactory(new PropertyValueFactory<>("orderItemName"));
        colOrderItemDescription.setCellValueFactory(new PropertyValueFactory<>("orderItemDescription"));
        colOrderDetailItemQty.setCellValueFactory(new PropertyValueFactory<>("orderDetailItemQty"));

        loadDate();
        txtOrderNumber.setText(generateNewOrderID());
        loadAllItemCodes();

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setItemDataTxt(newValue);
        });

        tblItemList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            orderAddItemRemove = (int) newValue;
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

    private void loadAllItemCodes() {
        try {
            ArrayList<StockItemDTO> all = confirmOrderBO.getAllItems();
            for (StockItemDTO dto : all) {
                cmbItemCode.getItems().add(dto.getItemCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemDataTxt(String itemCode) {
        try {
            StockItemDTO i1 = confirmOrderBO.searchItem(itemCode);
            if (i1 == null) {
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            } else {
                txtItemName.setText(i1.getItemName());
                txtItemDescription.setText(i1.getItemDescription());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        String orderNumber = txtOrderNumber.getText();
        String itemName = txtItemName.getText();
        String description = txtItemDescription.getText();
        int itemQty = Integer.parseInt(txtItemQty.getText());



        NewOrderItemListTM itemListTM = new NewOrderItemListTM(
                orderNumber,
                cmbItemCode.getValue(),
                itemName,
                description,
                itemQty
        );

        int rowNumber = isAddItem(itemListTM);

        if (rowNumber == -1) {
            obList.add(itemListTM);
        }else {
            NewOrderItemListTM temp = obList.get(rowNumber);
            NewOrderItemListTM newItemList = new NewOrderItemListTM(
                    temp.getOrderNumber(),
                    temp.getItemCode(),
                    temp.getItemName(),
                    temp.getItemDescription(),
                    temp.getItemQty() + itemQty
            );
            obList.remove(rowNumber);
            obList.add(newItemList);
        }
        tblItemList.setItems(obList);
        cmbItemCode.requestFocus();
        txtItemName.clear();
        txtItemDescription.clear();
        txtItemQty.clear();
    }

    private int isAddItem(NewOrderItemListTM itemListTM) {
        for (int i = 0; i < obList.size(); i++) {
            if (itemListTM.getItemCode().equals(obList.get(i).getItemCode())) {
                return i;
            }
        }
        return -1;
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
        if (orderAddItemRemove == -1) {
            new Alert(Alert.AlertType.WARNING,"Please Select a Row").show();
        }else {
            obList.remove(orderAddItemRemove);
            tblItemList.refresh();
        }
    }

    public void btnConfirmOrderOnAction(ActionEvent actionEvent) {
    }
}
