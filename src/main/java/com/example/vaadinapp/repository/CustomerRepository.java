package com.example.vaadinapp.repository;

import com.example.vaadinapp.model.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
    
    Flux<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
        String firstName, String lastName);
    
    @Query("SELECT * FROM customer WHERE " +
           "LOWER(first_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(last_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(city) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(country) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Flux<Customer> searchCustomers(String searchTerm);
    
    Flux<Customer> findByEmail(String email);
}
