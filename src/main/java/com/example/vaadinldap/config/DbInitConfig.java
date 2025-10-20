package com.example.vaadinldap.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.core.DatabaseClient;

import reactor.core.publisher.Flux;

@Configuration
public class DbInitConfig {

    @Bean
    ApplicationRunner initializeSchema(DatabaseClient client) {
        return args -> {
            ClassPathResource resource = new ClassPathResource("db/schema.sql");
            if (!resource.exists()) {
                return;
            }
            try (InputStream is = resource.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String sql = reader.lines().collect(Collectors.joining("\n"));
                List<String> statements = Arrays.stream(sql.split(";"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());

                Flux.fromIterable(statements)
                    .concatMap(stmt -> client.sql(stmt).fetch().rowsUpdated())
                    .then()
                    .subscribe();
            } catch (IOException e) {
                // In case schema cannot be loaded, fail silently; repository may still create tables
            }
        };
    }
}
