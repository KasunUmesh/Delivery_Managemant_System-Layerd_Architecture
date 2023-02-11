package bo.custom.impl;

import bo.custom.SoldDetailBO;
import dao.DAOFactory;
import dao.custom.CompanyOrderDAO;
import dao.custom.CustomerDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.StockItemDAO;
import db.DBConnection;
import dto.CustomerDTO;
import dto.SoldDetailsDTO;
import dto.StockItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SoldDetailBOImpl implements SoldDetailBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final StockItemDAO stockItemDAO = (StockItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCKITEM);
    private final CompanyOrderDAO companyOrderDAO = (CompanyOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean ConfirmSold(SoldDetailsDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;

        connection = DBConnection.getDbConnection().getConnection();
        boolean soldAvailable
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() {
        return null;
    }

    @Override
    public ArrayList<StockItemDTO> getAllItems() {
        return null;
    }

    @Override
    public StockItemDTO searchItem(String code) {
        return null;
    }

    @Override
    public CustomerDTO searchCustomer(String s) {
        return null;
    }
}
