package com.thenews.userprofile.domain.service;

import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface NutricionistService {

    Page<Nutricionist> getAllNutricionists(Pageable pageable);
    Nutricionist getNutricionistById(Long nutricionistId);

    Nutricionist createNutricionist(Nutricionist nutricionist);
    Nutricionist updateNutricionist(Long nutricionistId, Nutricionist nutricionistRequest);
    ResponseEntity<?> deleteNutricionist(Long nutricionistId);
}
