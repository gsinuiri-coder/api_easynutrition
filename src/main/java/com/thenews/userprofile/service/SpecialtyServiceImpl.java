package com.thenews.userprofile.service;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.userprofile.domain.model.Specialty;
import com.thenews.userprofile.domain.repository.NutricionistRepository;
import com.thenews.userprofile.domain.repository.SpecialtyRepository;
import com.thenews.userprofile.domain.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private NutricionistRepository nutricionistRepository;

    @Override
    public Page<Specialty> getAllSpecialtiesByNutricionistId(Long nutricionistId, Pageable pageable) {
        return specialtyRepository.findByNutricionistId(nutricionistId, pageable);
    }

    @Override
    public Specialty getSpecialtyByIdAndNutricionistId(Long nutricionistId, Long specialtyId) {
        return specialtyRepository.findByIdAndNutricionistId(specialtyId, nutricionistId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Specialty not found with Id " + specialtyId +
                                " and NutricionistId " + nutricionistId));
    }

    @Override
    public Specialty createSpecialty(Long nutricionistId, Specialty specialty) {
        return nutricionistRepository.findById(nutricionistId).map(nutricionist -> {
            specialty.setNutricionist(nutricionist);
            return specialtyRepository.save(specialty);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Nutricionist", "Id", nutricionistId));
    }

    @Override
    public Specialty updateSpecialty(Long nutricionistId, Long specialtyId, Specialty specialtyDetails) {
        if(!nutricionistRepository.existsById(nutricionistId))
            throw new ResourceNotFoundException("Nutricionist", "Id", nutricionistId);

        return specialtyRepository.findById(specialtyId).map(specialty -> {
            specialty.setName(specialtyDetails.getName());
            return specialtyRepository.save(specialty);
        }).orElseThrow(() -> new ResourceNotFoundException("Specialty", "Id", specialtyId));
    }

    @Override
    public ResponseEntity<?> deleteSpecialty(Long nutricionistId, Long specialtyId) {
        return specialtyRepository.findByIdAndNutricionistId(specialtyId, nutricionistId).map(specialty -> {
            specialtyRepository.delete(specialty);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Specialty not found with Id " + specialtyId + " and NutricionistId " + nutricionistId));
    }
}
