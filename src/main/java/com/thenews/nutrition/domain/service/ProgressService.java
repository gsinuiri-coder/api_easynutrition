package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Progress;
import org.springframework.http.ResponseEntity;

public interface ProgressService {

    Progress getProgressByIdAndSessionId(Long sessionId, Long progressId);
    Progress createProgress(Long sessionId, Progress progress);
    Progress updateProgress(Long sessionId, Long progressId, Progress progressRequest);
    ResponseEntity<?> deleteProgress(Long sessionId, Long progressId);

}
