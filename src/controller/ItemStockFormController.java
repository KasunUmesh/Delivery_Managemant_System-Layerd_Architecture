package controller;

import bo.BoFactory;
import bo.custom.StockItemBO;
import com.jfoenix.controls.JFXTextField;
import dto.StockItemDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import view.tdm.StockItemTM;

import java.sql.SQLException;

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
    }

    public void txtSearchOnKeyRelease(KeyEvent keyEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
    }
}
