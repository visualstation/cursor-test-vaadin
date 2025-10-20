package com.example.vaadinapp.service;

import com.example.vaadinapp.model.Customer;
import com.example.vaadinapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Mono<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    
    public Mono<Customer> saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public Mono<Void> deleteCustomer(Long id) {
        return customerRepository.deleteById(id);
    }
    
    public Flux<Customer> searchCustomers(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllCustomers();
        }
        return customerRepository.searchCustomers(searchTerm.trim());
    }
    
    /**
     * Blocking method for Vaadin UI integration
     * Converts reactive Flux to List
     */
    public List<Customer> getAllCustomersBlocking() {
        return getAllCustomers().collectList().block();
    }
    
    /**
     * Blocking method for Vaadin UI integration
     * Converts reactive Flux to List for search
     */
    public List<Customer> searchCustomersBlocking(String searchTerm) {
        return searchCustomers(searchTerm).collectList().block();
    }
    
    /**
     * Blocking method for Vaadin UI integration
     */
    public Customer saveCustomerBlocking(Customer customer) {
        return saveCustomer(customer).block();
    }
    
    /**
     * Blocking method for Vaadin UI integration
     */
    public void deleteCustomerBlocking(Long id) {
        deleteCustomer(id).block();
    }
}
