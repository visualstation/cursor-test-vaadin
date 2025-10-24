package com.example.vaadinlog.bootstrap;

import com.example.vaadinlog.model.LogEntry;
import com.example.vaadinlog.repository.LogEntryRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final LogEntryRepository repository;

    public DataLoader(LogEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            return;
        }
        List<LogEntry> entries = readLogsFromResource("/data/logs.txt");
        repository.saveAll(entries);
        log.info("Loaded {} log entries", entries.size());
    }

    private List<LogEntry> readLogsFromResource(String resourcePath) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }
        List<LogEntry> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                if (parts.length != 6) {
                    log.warn("Skipping malformed line: {}", line);
                    continue;
                }
                LogEntry entry = new LogEntry();
                entry.setEventTimestamp(LocalDateTime.parse(parts[0].trim(), formatter));
                entry.setCustomerId(parts[1].trim());
                entry.setUsername(parts[2].trim());
                entry.setMessage(parts[3].trim());
                try {
                    entry.setSeconds(Integer.parseInt(parts[4].trim()));
                } catch (NumberFormatException e) {
                    entry.setSeconds(null);
                }
                try {
                    entry.setBilledSeconds(Integer.parseInt(parts[5].trim()));
                } catch (NumberFormatException e) {
                    entry.setBilledSeconds(null);
                }
                list.add(entry);
            }
        }
        return list;
    }
}
