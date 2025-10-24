package com.example.vaadinlog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_entries", indexes = {
        @Index(name = "idx_logentry_event_time", columnList = "event_time")
})
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_time")
    private LocalDateTime eventTimestamp;

    @Column(length = 32)
    private String customerId;

    @Column(length = 128)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String message;

    private Integer seconds;

    private Integer billedSeconds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(LocalDateTime eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Integer getBilledSeconds() {
        return billedSeconds;
    }

    public void setBilledSeconds(Integer billedSeconds) {
        this.billedSeconds = billedSeconds;
    }
}
