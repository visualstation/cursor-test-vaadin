package com.example.logviewer.repository;

import com.example.logviewer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByCustomerCode(String customerCode);
    
    Optional<Customer> findByName(String name);
}
