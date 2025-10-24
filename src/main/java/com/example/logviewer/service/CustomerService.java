package com.example.logviewer.service;

import com.example.logviewer.entity.Customer;
import com.example.logviewer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository repository;
    
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
    
    public Optional<Customer> getCustomerById(Long id) {
        return repository.findById(id);
    }
    
    public Optional<Customer> getCustomerByCode(String customerCode) {
        return repository.findByCustomerCode(customerCode);
    }
    
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }
    
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
