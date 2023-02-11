package bo.custom.impl;

import bo.custom.SoldDetailBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DBConnection;
import dto.CustomerDTO;
import dto.SoldDetailsDTO;
import dto.StockItemDTO;
import dto.VehicleDTO;
import entity.Customers;
import entity.SoldDetails;
import entity.StockItem;
import entity.Vehicle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SoldDetailBOImpl implements SoldDetailBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final StockItemDAO stockItemDAO = (StockItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCKITEM);
    private final VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    private final SoldDetailsDAO soldDetailsDAO = (SoldDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SOLD_DETAILS);

    @Override
    public boolean ConfirmSold(SoldDetailsDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;

        connection = DBConnection.getDbConnection().getConnection();

        connection.setAutoCommit(false);
        SoldDetails soldDetails = new SoldDetails(dto.getCustomerID(), dto.getSoldDate(), dto.getItemCode(), dto.getSoldItemQTY(), dto.getSoldTotalPrice(), dto.getVehicleID());
        boolean soldAdded = soldDetailsDAO.add(soldDetails);
        if (!soldAdded) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        StockItem search = (StockItem) stockItemDAO.search(dto.getItemCode());
        search.setQtyOnStock(search.getQtyOnStock() - dto.getSoldItemQTY());
        boolean update = stockItemDAO.update(search);
        if (!update) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customers> all = customerDAO.getAll();
        for (Customers c : all) {
            allCustomers.add(new CustomerDTO(c.getCustomerID(), c.getCustomerName(), c.getShopName(), c.getAddress(), c.getContactNumber()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<StockItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<StockItemDTO> allItems = new ArrayList<>();
        ArrayList<StockItem> all = stockItemDAO.getAll();
        for (StockItem i : all) {
            allItems.add(new StockItemDTO(i.getItemCode(), i.getItemName(), i.getItemDescription(), i.getUnitPrice(), i.getQtyOnStock()));
        }
        return allItems;
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException {
       ArrayList<VehicleDTO> allVehicle = new ArrayList<>();
       ArrayList<Vehicle> all = vehicleDAO.getAll();
       for (Vehicle v : all) {
           allVehicle.add(new VehicleDTO(v.getVehicleID(), v.getVehicleNumber(), v.getVehicleType(), v.getDescription()));
       }
       return allVehicle;
    }

    @Override
    public StockItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        StockItem stockItem = (StockItem) stockItemDAO.search(code);
        return new StockItemDTO(stockItem.getItemCode(), stockItem.getItemName(), stockItem.getItemDescription(), stockItem.getUnitPrice(), stockItem.getQtyOnStock());
    }

    @Override
    public CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException {
        Customers c = (Customers) customerDAO.search(s);
        return new CustomerDTO(c.getCustomerID(), c.getCustomerName(), c.getShopName(), c.getAddress(), c.getContactNumber());
    }
}
