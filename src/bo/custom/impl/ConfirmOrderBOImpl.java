package bo.custom.impl;

import bo.custom.ConfirmOrderBO;
import dao.DAOFactory;
import dao.custom.CompanyOrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.StockItemDAO;
import db.DBConnection;
import dto.CompanyOrderDTO;
import dto.PlaceOrderDTO;
import dto.StockItemDTO;
import entity.CompanyOrder;
import entity.PlaceOrder;
import entity.StockItem;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConfirmOrderBOImpl implements ConfirmOrderBO {

    private final StockItemDAO stockItemDAO = (StockItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCKITEM);
    private final CompanyOrderDAO companyOrderDAO = (CompanyOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean ConfirmOrder(CompanyOrderDTO dto) throws SQLException, ClassNotFoundException {

        Connection connection = null;

        connection = DBConnection.getDbConnection().getConnection();
        boolean orderAvailable = companyOrderDAO.ifOrderExist(dto.getOrderNumber());

        if (orderAvailable) {
            return false;
        }

        connection.setAutoCommit(false);

        CompanyOrder companyOrder = new CompanyOrder(dto.getOrderNumber(), dto.getOrderDate(), dto.getOrderItemQTY());
        boolean orderAdded = companyOrderDAO.add(companyOrder);
        if (!orderAdded) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (PlaceOrderDTO detail : dto.getOrderDetail()) {
            PlaceOrder placeOrder = new PlaceOrder(dto.getOrderNumber(), detail.getItemCode(), detail.getItemQTY());
            boolean orderDetailsAdded = orderDetailsDAO.add(placeOrder);
            if (!orderDetailsAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
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
