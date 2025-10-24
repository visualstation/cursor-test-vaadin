package com.example.logviewer.service;

import com.example.logviewer.entity.Session;
import com.example.logviewer.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    
    @Autowired
    private SessionRepository repository;
    
    public List<Session> getAllSessions() {
        return repository.findAll();
    }
    
    public Optional<Session> getSessionById(Long id) {
        return repository.findById(id);
    }
    
    public Optional<Session> getSessionBySessionId(String sessionId) {
        return repository.findBySessionId(sessionId);
    }
    
    public List<Session> getSessionsByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }
    
    public List<Session> getSessionsByStatus(String status) {
        return repository.findByStatus(status);
    }
    
    public Session saveSession(Session session) {
        return repository.save(session);
    }
    
    public void deleteSession(Long id) {
        repository.deleteById(id);
    }
}
