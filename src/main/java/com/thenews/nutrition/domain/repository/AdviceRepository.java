package com.thenews.nutrition.domain.repository;


import com.thenews.nutrition.domain.model.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
    List<Advice> findByCustomerId(Long customerId);
    Optional<Advice> findByIdAndCustomerId(Long id, Long customerId);
}
