package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Progress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProgressService {

    List<Progress> getAllProgresssByAdviceId(Long adviceId);
    Progress getProgressByIdAndAdviceId(Long adviceId, Long progressId);
    Progress createProgress(Long adviceId, Progress progress);
    Progress updateProgress(Long adviceId, Long progressId, Progress progressDetails);
    ResponseEntity<?> deleteProgress(Long adviceId, Long progressId);

}
