package bo.custom.impl;

import bo.custom.StockItemBO;
import dao.DAOFactory;
import dao.custom.StockItemDAO;
import dto.StockItemDTO;
import entity.StockItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tdm.StockItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockItemBOImpl implements StockItemBO {

    private StockItemDAO stockItemDAO = (StockItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCKITEM);

    @Override
    public ArrayList<StockItemDTO> getAllStockItem() throws SQLException, ClassNotFoundException {
        ArrayList<StockItemDTO> allItems = new ArrayList<>();
        ArrayList<StockItem> all = stockItemDAO.getAll();
        for (StockItem stockItem : all) {
            allItems.add(new StockItemDTO(stockItem.getItemCode(), stockItem.getItemName(), stockItem.getItemDescription(), stockItem.getUnitPrice(), stockItem.getQtyOnStock()));
        }
        return allItems;
    }

    @Override
    public boolean addItem(StockItemDTO dto) throws SQLException, ClassNotFoundException {
        return stockItemDAO.add(new StockItem(dto.getItemCode(), dto.getItemName(), dto.getItemDescription(), dto.getUnitPrice(), dto.getQtyOnStock()));
    }

    @Override
    public boolean updateItem(StockItemDTO dto) throws SQLException, ClassNotFoundException {
        return stockItemDAO.update(new StockItem(dto.getItemCode(), dto.getItemName(), dto.getItemDescription(), dto.getUnitPrice(), dto.getQtyOnStock()));
    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        return stockItemDAO.delete(itemCode);
    }

    @Override
    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
        return stockItemDAO.generateNewID();
    }

    @Override
    public ObservableList<StockItemTM> searchStockItem(String s) throws SQLException, ClassNotFoundException {
        ObservableList<StockItemTM> allItems = FXCollections.observableArrayList();
        ObservableList<StockItem> stockItems = stockItemDAO.search(s);

        for (StockItem stockItem : stockItems) {
            allItems.add(new StockItemTM(stockItem.getItemCode(), stockItem.getItemName(), stockItem.getItemDescription(), stockItem.getUnitPrice(), stockItem.getQtyOnStock()));
        }
        return allItems;
    }

}
