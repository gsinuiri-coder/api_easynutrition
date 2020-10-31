package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.Progress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Page<Progress> findByAdviceId(Long adviceId, Pageable pageable);
    Optional<Progress> findByIdAndAdviceId(Long id, Long adviceId);
}
