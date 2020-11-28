package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.Diet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DietRepository extends JpaRepository<Diet, Long>{
    List<Diet> findByNutricionistId(Long nutricionistId);
    Optional<Diet> findByIdAndNutricionistId(Long id, Long nutricionistId);
}
