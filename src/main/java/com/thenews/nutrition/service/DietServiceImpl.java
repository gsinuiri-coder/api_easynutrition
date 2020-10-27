package com.thenews.nutrition.service;

import com.thenews.nutrition.domain.model.Diet;
import com.thenews.nutrition.domain.repository.DietRepository;
import com.thenews.nutrition.domain.service.DietService;
import com.thenews.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    private DietRepository dietRepository;

    @Override
    public Page<Diet> getAllDiets(Pageable pageable) {
        return dietRepository.findAll(pageable);
    }

    @Override
    public Diet getDietById(Long dietId) {
        return dietRepository.findById(dietId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Diet", "Id", dietId));
    }

    @Override
    public Diet getDietByIdAndSessionId(Long postId, Long dietId) {
        return dietRepository.findByIdAndSessionId(dietId, postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Diet not found with Id " + dietId +
                                " and SessionId " + postId));
    }
    
    @Override
    public Diet createDiet(Diet diet) {
        return dietRepository.save(diet);
    }

    @Override
    public Diet updateDiet(Long dietId, Diet dietRequest) {
        Diet diet = dietRepository.findById(dietId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Diet", "Id", dietId));
        return dietRepository.save(
                diet.setTitle(dietRequest.getTitle())
                        .setDescription(dietRequest.getDescription())
                        );
    }

    @Override
    public ResponseEntity<?> deleteDiet(Long dietId) {
        Diet diet = dietRepository.findById(dietId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Diet", "Id", dietId));
        dietRepository.delete(diet);
        return ResponseEntity.ok().build();
    }
}
