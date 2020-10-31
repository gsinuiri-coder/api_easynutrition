package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Page<Session> findByAdviceId(Long adviceId, Pageable pageable);
    Optional<Session> findByIdAndAdviceId(Long id, Long adviceId);
}
