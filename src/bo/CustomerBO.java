package bo;

import dto.CustomerDTO;

public interface CustomerBO {
    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String customerID);
}
