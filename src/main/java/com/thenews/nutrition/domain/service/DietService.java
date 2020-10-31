package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Diet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DietService {
    Page<Diet> getAllDietsByNutricionistId(Long nutricionistId, Pageable pageable);
    Page<Diet> getAllDietsByAdviceId(Long adviceId, Pageable pageable);
    Diet getDietByIdAndNutricionistId(Long nutricionistId, Long dietId);
    Diet createDiet(Long nutricionistId, Diet diet);
    Diet updateDiet(Long nutricionistId, Long dietId, Diet dietDetails);
    ResponseEntity<?> deleteDiet(Long nutricionistId, Long dietId);
}
