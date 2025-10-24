package com.example.logviewer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String sessionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    @Column(nullable = false)
    private LocalDateTime startTime;
    
    @Column
    private LocalDateTime endTime;
    
    @Column
    private String status; // ACTIVE, COMPLETED, CANCELLED
    
    @Column(length = 1000)
    private String description;
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterventionLog> interventionLogs = new ArrayList<>();
    
    public Session(String sessionId, Customer customer, LocalDateTime startTime, String status, String description) {
        this.sessionId = sessionId;
        this.customer = customer;
        this.startTime = startTime;
        this.status = status;
        this.description = description;
    }
}
