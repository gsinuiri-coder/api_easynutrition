package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Diet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DietService {
    Page<Diet> getAllDiets(Pageable pageable);
    Diet getDietById(Long dietId);
    Diet getDietByIdAndSessionId(Long sessionId, Long dietId);

    Diet createDiet(Diet diet);
    Diet updateDiet(Long dietId, Diet dietRequest);
    ResponseEntity<?> deleteDiet(Long dietId);
}
