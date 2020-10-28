package com.thenews.nutrition.service;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.nutrition.domain.model.Progress;
import com.thenews.nutrition.domain.repository.ProgressRepository;
import com.thenews.nutrition.domain.repository.SessionRepository;
import com.thenews.nutrition.domain.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Progress getProgressByIdAndSessionId(Long sessionId, Long progressId) {
        return progressRepository.findByIdAndSessionId(progressId, sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Progress not found with Id " + progressId +
                                " and SessionId " + sessionId));
    }

    @Override
    public Progress createProgress(Long sessionId, Progress progress) {
        return sessionRepository.findById(sessionId).map(session -> {
            progress.setSession(session);
            return progressRepository.save(progress);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Session", "Id", sessionId));
    }

    @Override
    public Progress updateProgress(Long sessionId, Long progressId, Progress progressDetails) {
        if(!sessionRepository.existsById(sessionId))
            throw new ResourceNotFoundException("Session", "Id", sessionId);

        return progressRepository.findById(progressId).map(progress -> {
            progress.setWeight(progressDetails.getWeight());
            progress.setDescription(progressDetails.getDescription());
            return progressRepository.save(progress);
        }).orElseThrow(() -> new ResourceNotFoundException("Progress", "Id", progressId));
    }

    @Override
    public ResponseEntity<?> deleteProgress(Long sessionId, Long progressId) {
        return progressRepository.findByIdAndSessionId(progressId, sessionId).map(progress -> {
            progressRepository.delete(progress);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Progress not found with Id " + progressId + " and SessionId " + sessionId));
    }
}
