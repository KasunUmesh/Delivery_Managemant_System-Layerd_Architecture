package bo;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import dto.CustomerDTO;

public class CustomerBOImpl implements CustomerBO{
    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public void deleteCustomer(String customerID) {

    }
}
