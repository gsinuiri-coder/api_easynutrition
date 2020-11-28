package com.thenews.userprofile.domain.service;

import com.thenews.userprofile.domain.model.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpecialtyService {
    List<Specialty> getAllSpecialties();
    List<Specialty> getAllSpecialtiesByNutricionistId(Long nutricionistId);
    Specialty getSpecialtyById(Long specialtyId);
    Specialty createSpecialty(Specialty specialty);
    Specialty updateSpecialty(Long specialtyId, Specialty specialtyDetails);
    ResponseEntity<?> deleteSpecialty(Long specialtyId);
}
