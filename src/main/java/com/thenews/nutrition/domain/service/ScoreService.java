package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ScoreService {
//    Page<Score> getAllScoresByAdviceId(Long adviceId, Pageable pageable);
//    Score getScoreByIdAndAdviceId(Long adviceId, Long scoreId);

    Score getScoreByAdviceId(Long adviceId);
    Score createScore(Long adviceId, Score score);
    Score updateScore(Long adviceId, Long scoreId, Score scoreDetails);
    ResponseEntity<?> deleteScore(Long adviceId, Long scoreId);
}
