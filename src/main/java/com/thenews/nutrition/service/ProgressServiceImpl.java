package com.thenews.nutrition.service;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.nutrition.domain.model.Progress;
import com.thenews.nutrition.domain.repository.AdviceRepository;
import com.thenews.nutrition.domain.repository.ProgressRepository;
import com.thenews.nutrition.domain.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressRepository sessionRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @Override
    public Page<Progress> getAllProgresssByAdviceId(Long adviceId, Pageable pageable) {
        return sessionRepository.findByAdviceId(adviceId, pageable);
    }

    @Override
    public Progress getProgressByIdAndAdviceId(Long adviceId, Long sessionId) {
        return sessionRepository.findByIdAndAdviceId(sessionId, adviceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Progress not found with Id " + sessionId +
                                " and AdviceId " + adviceId));
    }

    @Override
    public Progress createProgress(Long adviceId, Progress session) {
        return adviceRepository.findById(adviceId).map(advice -> {
            session.setAdvice(advice);
            return sessionRepository.save(session);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Advice", "Id", adviceId));
    }

    @Override
    public Progress updateProgress(Long adviceId, Long sessionId, Progress sessionDetails) {
        if(!adviceRepository.existsById(adviceId))
            throw new ResourceNotFoundException("Advice", "Id", adviceId);

        return sessionRepository.findById(sessionId).map(session -> {
            session.setWeight(sessionDetails.getWeight());
            session.setDescription(sessionDetails.getDescription());
            return sessionRepository.save(session);
        }).orElseThrow(() -> new ResourceNotFoundException("Progress", "Id", sessionId));
    }

    @Override
    public ResponseEntity<?> deleteProgress(Long adviceId, Long sessionId) {
        return sessionRepository.findByIdAndAdviceId(sessionId, adviceId).map(session -> {
            sessionRepository.delete(session);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Progress not found with Id " + sessionId + " and AdviceId " + adviceId));
    }
}
