package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Diet;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DietService {
    List<Diet> getAllDietsByNutricionistId(Long nutricionistId);
    List<Diet> getAllDietsByAdviceId(Long adviceId);
    Diet getDietByIdAndNutricionistId(Long nutricionistId, Long dietId);
    Diet createDiet(Long nutricionistId, Diet diet);
    Diet updateDiet(Long nutricionistId, Long dietId, Diet dietDetails);
    ResponseEntity<?> deleteDiet(Long nutricionistId, Long dietId);
}
