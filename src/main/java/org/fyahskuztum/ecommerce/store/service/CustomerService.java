package org.fyahskuztum.ecommerce.store.service;

import org.fyahskuztum.ecommerce.store.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long custId);
    Optional<Customer> getCustomerByEmail(String email);
    void addNewCustomer(Customer cust);
    void updateCustomer(Customer updatedCust);
    void deleteCustomerById(Long custId);
}
