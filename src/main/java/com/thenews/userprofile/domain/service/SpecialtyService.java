package com.thenews.userprofile.domain.service;

import com.thenews.userprofile.domain.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SpecialtyService {
    Page<Specialty> getAllSpecialtiesByNutricionistId(Long nutricionistId, Pageable pageable);
    Specialty getSpecialtyByIdAndNutricionistId(Long nutricionistId, Long specialtyId);
    Specialty createSpecialty(Long nutricionistId, Specialty specialty);
    Specialty updateSpecialty(Long nutricionistId, Long specialtyId, Specialty specialtyDetails);
    ResponseEntity<?> deleteSpecialty(Long nutricionistId, Long specialtyId);
}
