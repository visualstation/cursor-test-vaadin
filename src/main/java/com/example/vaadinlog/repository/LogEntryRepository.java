package com.example.vaadinlog.repository;

import com.example.vaadinlog.model.LogEntry;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
    // Additional query methods can be added here if needed
}
