package com.thenews.nutrition.service;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.nutrition.domain.model.Score;
import com.thenews.nutrition.domain.repository.AdviceRepository;
import com.thenews.nutrition.domain.repository.ScoreRepository;
import com.thenews.nutrition.domain.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private AdviceRepository adviceRepository;

//    @Override
//    public Page<Score> getAllScoresByAdviceId(Long adviceId, Pageable pageable) {
//        return scoreRepository.findByAdviceId(adviceId, pageable);
//    }

//    @Override
//    public Score getScoreByIdAndAdviceId(Long adviceId, Long scoreId) {
//        return scoreRepository.findByIdAndAdviceId(scoreId, adviceId)
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        "Score not found with Id " + scoreId +
//                                " and AdviceId " + adviceId));
//    }

    @Override
    public Score getScoreByAdviceId(Long adviceId) {
        return scoreRepository.findByAdviceId(adviceId);
    }

    @Override
    public Score createScore(Long adviceId, Score score) {
        return adviceRepository.findById(adviceId).map(advice -> {
            score.setAdvice(advice);
            return scoreRepository.save(score);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Advice", "Id", adviceId));
    }

    @Override
    public Score updateScore(Long adviceId, Long scoreId, Score scoreDetails) {
        if(!adviceRepository.existsById(adviceId))
            throw new ResourceNotFoundException("Advice", "Id", adviceId);

        return scoreRepository.findById(scoreId).map(score -> {
            score.setStar(scoreDetails.getStar());
            score.setDescription(scoreDetails.getDescription());
            return scoreRepository.save(score);
        }).orElseThrow(() -> new ResourceNotFoundException("Score", "Id", scoreId));
    }

    @Override
    public ResponseEntity<?> deleteScore(Long adviceId, Long scoreId) {
        return scoreRepository.findByIdAndAdviceId(scoreId, adviceId).map(score -> {
            scoreRepository.delete(score);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Score not found with Id " + scoreId + " and AdviceId " + adviceId));
    }
}
