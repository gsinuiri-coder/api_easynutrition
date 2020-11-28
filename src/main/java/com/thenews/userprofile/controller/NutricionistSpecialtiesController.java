package com.thenews.userprofile.controller;

import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.model.Specialty;
import com.thenews.userprofile.domain.service.NutricionistService;
import com.thenews.userprofile.domain.service.SpecialtyService;
import com.thenews.userprofile.resource.NutricionistResource;
import com.thenews.userprofile.resource.SaveNutricionistResource;
import com.thenews.userprofile.resource.SaveSpecialtyResource;
import com.thenews.userprofile.resource.SpecialtyResource;
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
@Tag(name = "Nutricionists", description = "Nutricionists API")
@RestController
@RequestMapping("/api")
public class NutricionistSpecialtiesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private NutricionistService nutricionistService;

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping("/nutricionists/{nutricionistId}/specialties/{specialtyId}")
    public NutricionistResource assignNutricionistSpecialty(
            @PathVariable(name = "nutricionistId") Long nutricionistId,
            @PathVariable(name = "specialtyId") Long specialtyId) {
        return convertToResource(nutricionistService.assignNutricionistSpecialty(nutricionistId, specialtyId));
    }

    @DeleteMapping("/nutricionists/{nutricionistId}/specialties/{specialtyId}")
    public NutricionistResource unassignNutricionistSpecialty(
            @PathVariable(name = "nutricionistId") Long nutricionistId,
            @PathVariable(name = "specialtyId") Long specialtyId) {
        return convertToResource(nutricionistService.unassignNutricionistSpecialty(nutricionistId, specialtyId));
    }

    @GetMapping("/nutricionists/{nutricionistId}/specialties")
    public List<SpecialtyResource> getAllSpecialtiesByNutricionistId(@PathVariable(name = "nutricionistId") Long nutricionistId) {
        return specialtyService.getAllSpecialtiesByNutricionistId(nutricionistId)
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    private Specialty convertToEntity(SaveSpecialtyResource resource) {
        return mapper.map(resource, Specialty.class);
    }
    private SpecialtyResource convertToResource(Specialty entity) {
        return mapper.map(entity, SpecialtyResource.class);
    }

    private Nutricionist convertToEntity(SaveNutricionistResource resource) {
        return mapper.map(resource, Nutricionist.class);
    }
    private NutricionistResource convertToResource(Nutricionist entity) {
        return mapper.map(entity, NutricionistResource.class);
    }
}
