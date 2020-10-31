package com.thenews.nutrition.service;

import com.thenews.nutrition.domain.model.Session;
import com.thenews.nutrition.domain.repository.AdviceRepository;
import com.thenews.nutrition.domain.repository.SessionRepository;
import com.thenews.nutrition.domain.service.SessionService;
import com.thenews.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @Override
    public Page<Session> getAllSessionsByAdviceId(Long adviceId, Pageable pageable) {
        return sessionRepository.findByAdviceId(adviceId, pageable);
    }

    @Override
    public Session getSessionByIdAndAdviceId(Long adviceId, Long sessionId) {
        return sessionRepository.findByIdAndAdviceId(sessionId, adviceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session not found with Id " + sessionId +
                                " and AdviceId " + adviceId));
    }

    @Override
    public Session createSession(Long adviceId, Session session) {
        return adviceRepository.findById(adviceId).map(advice -> {
            session.setAdvice(advice);
            return sessionRepository.save(session);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Advice", "Id", adviceId));
    }

    @Override
    public Session updateSession(Long adviceId, Long sessionId, Session sessionDetails) {
        if(!adviceRepository.existsById(adviceId))
            throw new ResourceNotFoundException("Advice", "Id", adviceId);

        return sessionRepository.findById(sessionId).map(session -> {
            session.setStartAt(sessionDetails.getStartAt());
            session.setLink(sessionDetails.getLink());
            return sessionRepository.save(session);
        }).orElseThrow(() -> new ResourceNotFoundException("Session", "Id", sessionId));
    }

    @Override
    public ResponseEntity<?> deleteSession(Long adviceId, Long sessionId) {
        return sessionRepository.findByIdAndAdviceId(sessionId, adviceId).map(session -> {
            sessionRepository.delete(session);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Session not found with Id " + sessionId + " and AdviceId " + adviceId));
    }
}
