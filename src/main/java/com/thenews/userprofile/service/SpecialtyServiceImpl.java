package com.thenews.userprofile.service;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.model.Specialty;
import com.thenews.userprofile.domain.repository.NutricionistRepository;
import com.thenews.userprofile.domain.repository.SpecialtyRepository;
import com.thenews.userprofile.domain.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private NutricionistRepository nutricionistRepository;

    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    @Override
    public List<Specialty> getAllSpecialtiesByNutricionistId(Long nutricionistId) {
        return nutricionistRepository.findById(nutricionistId).map(Nutricionist::getSpecialties)
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionist", "Id", nutricionistId));
    }

    @Override
    public Specialty getSpecialtyById(Long specialtyId) {
        return specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Specialty", "Id", specialtyId));
    }

    @Override
    public Specialty createSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty updateSpecialty(Long specialtyId, Specialty specialtyDetails) {
        return specialtyRepository.findById(specialtyId).map(specialty -> {
            specialty.setName(specialtyDetails.getName());
            return specialtyRepository.save(specialty);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Specialty", "Id", specialtyId));
    }

    @Override
    public ResponseEntity<?> deleteSpecialty(Long specialtyId) {
        return specialtyRepository.findById(specialtyId).map(specialty -> {
            specialtyRepository.delete(specialty);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Specialty", "Id", specialtyId));
    }
}
