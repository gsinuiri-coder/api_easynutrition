package com.thenews.userprofile.domain.service;

import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NutricionistService {

    List<Nutricionist> getAllNutricionists();
    Nutricionist getNutricionistById(Long nutricionistId);

    Nutricionist createNutricionist(Nutricionist nutricionist);
    Nutricionist updateNutricionist(Long nutricionistId, Nutricionist nutricionistRequest);
    ResponseEntity<?> deleteNutricionist(Long nutricionistId);

    Nutricionist assignNutricionistSpecialty(Long nutricionistId, Long specialtyId);
    Nutricionist unassignNutricionistSpecialty(Long nutricionistId, Long specialtyId);
    List<Nutricionist> getAllNutricionistsBySpecialtyId(Long specialtyId);
}
