package com.thenews.userprofile.service;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.model.Specialty;
import com.thenews.userprofile.domain.repository.NutricionistRepository;
import com.thenews.userprofile.domain.repository.SpecialtyRepository;
import com.thenews.userprofile.domain.service.NutricionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutricionistServiceImpl implements NutricionistService {
    
    @Autowired
    private NutricionistRepository nutricionistRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<Nutricionist> getAllNutricionists() {
        return nutricionistRepository.findAll();
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

    @Override
    public Nutricionist assignNutricionistSpecialty(Long nutricionistId, Long specialtyId) {
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Specialty", "Id", specialtyId));
        return nutricionistRepository.findById(nutricionistId).map(nutricionist -> {
            return nutricionistRepository.save(nutricionist.tagWith(specialty));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Nutricionist", "Id", nutricionistId));

    }

    @Override
    public Nutricionist unassignNutricionistSpecialty(Long nutricionistId, Long specialtyId) {
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Specialty", "Id", specialtyId));
        return nutricionistRepository.findById(nutricionistId).map(nutricionist -> {
            return nutricionistRepository.save(nutricionist.unTagWith(specialty));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Nutricionist", "Id", nutricionistId));
    }

    @Override
    public List<Nutricionist> getAllNutricionistsBySpecialtyId(Long specialtyId) {
        return specialtyRepository.findById(specialtyId).map(Specialty::getNutricionists)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", "Id", specialtyId));
    }
}
