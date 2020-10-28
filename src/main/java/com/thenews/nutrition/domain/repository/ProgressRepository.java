package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByIdAndSessionId(Long id, Long sessionId);
}
