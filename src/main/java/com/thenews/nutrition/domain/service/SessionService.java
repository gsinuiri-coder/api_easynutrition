package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SessionService {
    List<Session> getAllSessionsByAdviceId(Long adviceId);
    Session getSessionByIdAndAdviceId(Long adviceId, Long sessionId);
    Session createSession(Long adviceId, Session session);
    Session updateSession(Long adviceId, Long sessionId, Session sessionDetails);
    ResponseEntity<?> deleteSession(Long adviceId, Long sessionId);
}
