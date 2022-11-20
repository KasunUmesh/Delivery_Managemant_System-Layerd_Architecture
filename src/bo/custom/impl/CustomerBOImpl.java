package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customers> all = customerDAO.getAll();
        for (Customers customers : all) {
            allCustomers.add(new CustomerDTO(customers.getCustomerID(), customers.getCustomerName(), customers.getShopName(), customers.getAddress(), customers.getContactNumber()));
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customers(dto.getCustomerID(), dto.getCustomerName(), dto.getShopName(), dto.getAddress(), dto.getContactNumber()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customers(dto.getCustomerID(), dto.getCustomerName(), dto.getShopName(), dto.getAddress(), dto.getContactNumber()));
    }

    @Override
    public boolean deleteCustomer(String customerID) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerID);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public ObservableList<CustomerTM> searchCustomer(String s) throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTM> allCustomer = FXCollections.observableArrayList();
        ObservableList<Customers> customer = customerDAO.search(s);

        for (Customers c : customer) {
            allCustomer.add(new CustomerTM(c.getCustomerID(), c.getCustomerName(), c.getShopName(), c.getAddress(), c.getContactNumber()));
        }
        return allCustomer;
    }


}
