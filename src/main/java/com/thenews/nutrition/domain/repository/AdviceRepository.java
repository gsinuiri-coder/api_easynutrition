package com.thenews.nutrition.domain.repository;


import com.thenews.nutrition.domain.model.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
    Page<Advice> findByCustomerId(Long customerId, Pageable pageable);
    Optional<Advice> findByIdAndCustomerId(Long id, Long customerId);

    Page<Advice> findByNutricionistId(Long nutricionistId, Pageable pageable);
    Optional<Advice> findByIdAndNutricionistId(Long id, Long nutricionistId);
}
