package com.thenews.nutrition.service;

import com.thenews.nutrition.domain.model.Advice;
import com.thenews.nutrition.domain.model.Diet;
import com.thenews.nutrition.domain.repository.AdviceRepository;
import com.thenews.nutrition.domain.repository.DietRepository;
import com.thenews.nutrition.domain.service.DietService;
import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.userprofile.domain.repository.NutricionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private NutricionistRepository nutricionistRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @Override
    public List<Diet> getAllDietsByNutricionistId(Long nutricionistId) {
        return dietRepository.findByNutricionistId(nutricionistId);
    }

    @Override
    public Diet getDietByIdAndNutricionistId(Long nutricionistId, Long dietId) {
        return dietRepository.findByIdAndNutricionistId(dietId, nutricionistId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Diet not found with Id " + dietId +
                                " and NutricionistId " + nutricionistId));
    }

    @Override
    public List<Diet> getAllDietsByAdviceId(Long adviceId) {
        return adviceRepository.findById(adviceId).map(Advice::getDiets)
                .orElseThrow(() -> new ResourceNotFoundException("Advice", "Id", adviceId));
    }

    @Override
    public Diet createDiet(Long nutricionistId, Diet diet) {
        return nutricionistRepository.findById(nutricionistId).map(nutricionist -> {
            diet.setNutricionist(nutricionist);
            return dietRepository.save(diet);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Nutricionist", "Id", nutricionistId));
    }

    @Override
    public Diet updateDiet(Long nutricionistId, Long dietId, Diet dietDetails) {
        if(!nutricionistRepository.existsById(nutricionistId))
            throw new ResourceNotFoundException("Nutricionist", "Id", nutricionistId);

        return dietRepository.findById(dietId).map(diet -> {
            diet.setTitle(dietDetails.getTitle());
            diet.setDescription(dietDetails.getDescription());
            return dietRepository.save(diet);
        }).orElseThrow(() -> new ResourceNotFoundException("Diet", "Id", dietId));
    }

    @Override
    public ResponseEntity<?> deleteDiet(Long nutricionistId, Long dietId) {
        return dietRepository.findByIdAndNutricionistId(dietId, nutricionistId).map(diet -> {
            dietRepository.delete(diet);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Diet not found with Id " + dietId + " and NutricionistId " + nutricionistId));
    }
}
