package com.thenews.userprofile.controller;

import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.service.NutricionistService;
import com.thenews.userprofile.resource.NutricionistResource;
import com.thenews.userprofile.resource.SaveNutricionistResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Tag(name = "Specialties", description = "Specialties API")
@RestController
@RequestMapping("/api")
public class SpecialtyNutricionistsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private NutricionistService nutricionistService;

    @GetMapping("/specialties/{specialtyId}/nutricionists")
    public List<NutricionistResource> getAllNutricionistsBySpecialtyId(@PathVariable(name = "specialtyId") Long specialtyId) {
        return nutricionistService.getAllNutricionistsBySpecialtyId(specialtyId)
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());

    }

    private Nutricionist convertToEntity(SaveNutricionistResource resource) {
        return mapper.map(resource, Nutricionist.class);
    }
    private NutricionistResource convertToResource(Nutricionist entity) {
        return mapper.map(entity, NutricionistResource.class);
    }
}
