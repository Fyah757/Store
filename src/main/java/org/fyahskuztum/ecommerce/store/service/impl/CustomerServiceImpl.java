package org.fyahskuztum.ecommerce.store.service.impl;

import jakarta.transaction.Transactional;
import org.fyahskuztum.ecommerce.store.model.Customer;
import org.fyahskuztum.ecommerce.store.repository.CustomerRepository;
import org.fyahskuztum.ecommerce.store.repository.SaleRepository;
import org.fyahskuztum.ecommerce.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SaleRepository saleRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findByOrderByFirstName();
    }

    public Optional<Customer> getCustomerById(Long custId) {
        return customerRepository.findById(custId);
    }

    public Optional<Customer> getCustomerByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    public void addNewCustomer(Customer cust) {
        customerRepository.save(cust);
    }

    public void updateCustomer(Customer updatedCust) {
        Optional<Customer> cust = customerRepository.findById(updatedCust.getId());

        if(cust.isPresent()) {
            Customer existingCust = cust.get();
            existingCust.setFirstName(updatedCust.getFirstName());
            existingCust.setLastName(updatedCust.getLastName());
            existingCust.setEmail(updatedCust.getEmail());
            customerRepository.save(existingCust);
        }
    }

    @Transactional
    public void deleteCustomerById(Long custId) {
        saleRepository.deleteByCustomerId(custId);
        customerRepository.deleteById(custId);
    }

}
