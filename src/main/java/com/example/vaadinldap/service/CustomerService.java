package com.example.vaadinldap.service;

import org.springframework.stereotype.Service;

import com.example.vaadinldap.domain.Customer;
import com.example.vaadinldap.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Flux<Customer> findAll() {
        return repository.findAll();
    }

    public Flux<Customer> search(String query) {
        if (query == null || query.isBlank()) {
            return findAll();
        }
        String q = query.trim();
        return repository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(q, q, q);
    }

    public Mono<Customer> save(Customer customer) {
        return repository.save(customer);
    }
}
