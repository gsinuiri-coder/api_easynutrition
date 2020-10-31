package com.thenews.userprofile.domain.repository;

import com.thenews.userprofile.domain.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Page<Specialty> findByNutricionistId(Long nutricionistId, Pageable pageable);
    Optional<Specialty> findByIdAndNutricionistId(Long id, Long nutricionistId);
}
