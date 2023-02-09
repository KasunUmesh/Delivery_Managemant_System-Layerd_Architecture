package controller;

import bo.BoFactory;
import bo.custom.StockItemBO;
import com.jfoenix.controls.JFXTextField;
import dto.StockItemDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import view.tdm.StockItemTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemStockFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQTYOnStock;
    public TableView<StockItemTM> tblItemStock;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnStock;
    public JFXTextField txtSearch;

    private final StockItemBO stockItemBO = (StockItemBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.STOCKITEM);

    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnStock.setCellValueFactory(new PropertyValueFactory<>("qtyOnStock"));

        loadAllStockItems();
        txtItemCode.setText(generateNewID());
        txtItemCode.setEditable(false);
    }

    private void loadAllStockItems() {
        tblItemStock.getItems().clear();

        try {
            ArrayList<StockItemDTO> allItems = stockItemBO.getAllStockItem();
            for (StockItemDTO items : allItems) {
                tblItemStock.getItems().add(new StockItemTM(items.getItemCode(), items.getItemName(), items.getItemDescription(), items.getUnitPrice(), items.getQtyOnStock()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnAddItemOnAction(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String itemDescription = txtItemDescription.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Integer qtyOnStock = Integer.valueOf(txtQTYOnStock.getText());


        try {
            StockItemDTO stockItemDTO = new StockItemDTO(itemCode, itemName, itemDescription, unitPrice, qtyOnStock);
            stockItemBO.addItem(stockItemDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();
            txtItemCode.setText(generateNewID());
            loadAllStockItems();

            txtItemName.clear();
            txtItemDescription.clear();
            txtUnitPrice.clear();
            txtQTYOnStock.clear();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Save the Item" + e.getMessage()).show();
            txtItemName.clear();
            txtItemDescription.clear();
            txtUnitPrice.clear();
            txtQTYOnStock.clear();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String itemDescription = txtItemDescription.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Integer qtyOnStock = Integer.valueOf(txtQTYOnStock.getText());


        try {
            StockItemDTO stockItemDTO = new StockItemDTO(itemCode, itemName, itemDescription, unitPrice, qtyOnStock);
            stockItemBO.updateItem(stockItemDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Success..").show();
            txtItemCode.setText(generateNewID());
            loadAllStockItems();

            txtItemName.clear();
            txtItemDescription.clear();
            txtUnitPrice.clear();
            txtQTYOnStock.clear();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the Items " + itemCode + e.getMessage()).show();
            txtItemName.clear();
            txtItemDescription.clear();
            txtUnitPrice.clear();
            txtQTYOnStock.clear();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtSearchOnKeyRelease(KeyEvent keyEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        String id = tblItemStock.getSelectionModel().getSelectedItem().getItemCode();

        try {
            stockItemBO.deleteItem(id);
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Success..").show();
            tblItemStock.getItems().remove(tblItemStock.getSelectionModel().getSelectedItem());
            tblItemStock.getSelectionModel().clearSelection();
            txtItemCode.setText(generateNewID());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Items" + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        StockItemTM selectedItems = tblItemStock.getSelectionModel().getSelectedItem();
        txtItemCode.setText(selectedItems.getItemCode());
        txtItemName.setText(selectedItems.getItemName());
        txtItemDescription.setText(selectedItems.getItemDescription());
        txtUnitPrice.setText(String.valueOf(selectedItems.getUnitPrice()));
        txtQTYOnStock.setText(String.valueOf(selectedItems.getQtyOnStock()));
    }

    private String generateNewID() {
        try {
            return stockItemBO.generateNewItemCode();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (tblItemStock.getItems().isEmpty()) {
            return "I001";
        }else {
            String id = getLastItemCode();
            int newItemCode = Integer.parseInt(id.replace("I", "")) +1;
            return String.format("I%03d", newItemCode);
        }
    }

    private String getLastItemCode() {
        List<StockItemTM> tempItemList = new ArrayList<>(tblItemStock.getItems());
        Collections.sort(tempItemList);
        return tempItemList.get(tempItemList.size() -1).getItemCode();
    }

}
