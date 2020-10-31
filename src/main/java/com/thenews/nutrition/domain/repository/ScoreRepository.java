package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository  extends JpaRepository<Score, Long> {
    Score findByAdviceId(Long adviceId);
    Optional<Score> findByIdAndAdviceId(Long id, Long adviceId);
}
