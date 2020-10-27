package com.thenews.userprofile.service;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.repository.NutricionistRepository;
import com.thenews.userprofile.domain.service.NutricionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NutricionistServiceImpl implements NutricionistService {
    
    @Autowired
    private NutricionistRepository nutricionistRepository;

    @Override
    public Page<Nutricionist> getAllNutricionists(Pageable pageable) {
        return nutricionistRepository.findAll(pageable);
    }

    @Override
    public Nutricionist getNutricionistById(Long nutricionistId) {
        return nutricionistRepository.findById(nutricionistId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nutricionist", "Id", nutricionistId));
    }

    @Override
    public Nutricionist createNutricionist(Nutricionist nutricionist) {
        return nutricionistRepository.save(nutricionist);
    }

    @Override
    public Nutricionist updateNutricionist(Long nutricionistId, Nutricionist nutricionistRequest) {
        Nutricionist nutricionist = nutricionistRepository.findById(nutricionistId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nutricionist", "Id", nutricionistId));
        return nutricionistRepository.save(
                nutricionist.setSeniorYear(nutricionistRequest.getSeniorYear()));
    }

    @Override
    public ResponseEntity<?> deleteNutricionist(Long nutricionistId) {
        Nutricionist nutricionist = nutricionistRepository.findById(nutricionistId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nutricionist", "Id", nutricionistId));
        nutricionistRepository.delete(nutricionist);
        return ResponseEntity.ok().build();
    }
}
