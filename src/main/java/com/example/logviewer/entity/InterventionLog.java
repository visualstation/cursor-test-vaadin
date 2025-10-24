package com.example.logviewer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "intervention_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterventionLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @Column(nullable = false)
    private String clientId;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false, length = 1000)
    private String description;
    
    @Column(nullable = false)
    private Integer duration;
    
    @Column(nullable = false)
    private Integer billedDuration;
    
    public InterventionLog(Session session, LocalDateTime timestamp, String clientId, String username, 
                          String description, Integer duration, Integer billedDuration) {
        this.session = session;
        this.timestamp = timestamp;
        this.clientId = clientId;
        this.username = username;
        this.description = description;
        this.duration = duration;
        this.billedDuration = billedDuration;
    }
}
