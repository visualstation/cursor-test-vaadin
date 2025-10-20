package com.example.vaadinldap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.vaadinldap.domain.Customer;
import com.example.vaadinldap.repository.CustomerRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner loadDemoData(CustomerRepository repository) {
        return args -> {
            repository.count()
                .filter(count -> count == 0)
                .flatMapMany(unused -> repository.saveAll(java.util.List.of(
                    new Customer(null, "Alice", "Anderson", "alice@example.com", "+1-555-1001"),
                    new Customer(null, "Bob", "Brown", "bob@example.com", "+1-555-1002"),
                    new Customer(null, "Charlie", "Clark", "charlie@example.com", "+1-555-1003")
                )))
                .subscribe();
        };
    }
}
