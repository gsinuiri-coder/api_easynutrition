package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.Progress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByAdviceId(Long adviceId);
    Optional<Progress> findByIdAndAdviceId(Long id, Long adviceId);
}
