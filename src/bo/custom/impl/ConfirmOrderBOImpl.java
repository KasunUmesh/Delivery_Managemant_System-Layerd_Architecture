package bo.custom.impl;

import bo.custom.ConfirmOrderBO;
import dao.DAOFactory;
import dao.custom.CompanyOrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.StockItemDAO;
import dto.CompanyOrderDTO;
import dto.StockItemDTO;
import entity.StockItem;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConfirmOrderBOImpl implements ConfirmOrderBO {

    private final StockItemDAO stockItemDAO = (StockItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCKITEM);
    private final CompanyOrderDAO companyOrderDAO = (CompanyOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean ConfirmOrder(CompanyOrderDTO dto) {
        return false;
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return companyOrderDAO.generateNewOrderId();
    }

    @Override
    public ArrayList<StockItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<StockItemDTO> allItems = new ArrayList<>();
        ArrayList<StockItem> all = stockItemDAO.getAll();
        for (StockItem stockItem : all) {
            allItems.add(new StockItemDTO(stockItem.getItemCode(), stockItem.getItemName(), stockItem.getItemDescription(), stockItem.getUnitPrice(), stockItem.getQtyOnStock()));
        }
        return allItems;
    }

    @Override
    public StockItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        StockItem stockItem = stockItemDAO.getItems(code);
        return new StockItemDTO(stockItem.getItemCode(), stockItem.getItemName(), stockItem.getItemDescription(), stockItem.getUnitPrice(), stockItem.getQtyOnStock());
    }
}
