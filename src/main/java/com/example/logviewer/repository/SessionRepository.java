package com.example.logviewer.repository;

import com.example.logviewer.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    
    Optional<Session> findBySessionId(String sessionId);
    
    List<Session> findByCustomerId(Long customerId);
    
    List<Session> findByStatus(String status);
}
