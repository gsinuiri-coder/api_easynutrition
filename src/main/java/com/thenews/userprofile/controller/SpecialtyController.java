package com.thenews.userprofile.controller;

import com.thenews.userprofile.domain.model.Specialty;
import com.thenews.userprofile.domain.service.SpecialtyService;
import com.thenews.userprofile.resource.SaveSpecialtyResource;
import com.thenews.userprofile.resource.SpecialtyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;



@Tag(name = "Specialties", description = "Specialties API")
@RestController
@RequestMapping("/api")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Specialties", description = "Get All available Specialties", responses = {
            @ApiResponse(
                    description = "All Specialties",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/nutricionists/{nutricionistId}/specialties")
    public Page<SpecialtyResource> getAllSpecialtiesByNutricionistId(
            @PathVariable(value = "nutricionistId") Long nutricionistId,
            Pageable pageable) {
        Page<Specialty> specialtyPage = specialtyService.getAllSpecialtiesByNutricionistId(nutricionistId, pageable);
        List<SpecialtyResource> resources = specialtyPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/nutricionists/{nutricionistId}/specialties/{specialtyId}")
    public SpecialtyResource getSpecialtyByIdAndNutricionistId(
            @PathVariable(name = "nutricionistId") Long nutricionistId,
            @PathVariable(name = "specialtyId") Long specialtyId) {
        return convertToResource(specialtyService.getSpecialtyByIdAndNutricionistId(nutricionistId, specialtyId));
    }

    @PostMapping("/nutricionists/{nutricionistId}/specialties")
    public SpecialtyResource createSpecialty(
            @PathVariable(value = "nutricionistId") Long nutricionistId,
            @Valid @RequestBody SaveSpecialtyResource resource) {
        return convertToResource(specialtyService.createSpecialty(nutricionistId,
                convertToEntity(resource)));
    }

    @PutMapping("/nutricionists/{nutricionistId}/specialties/{specialtyId}")
    public SpecialtyResource updateSpecialty(
            @PathVariable (value = "nutricionistId") Long nutricionistId,
            @PathVariable (value = "specialtyId") Long specialtyId,
            @Valid @RequestBody SaveSpecialtyResource resource) {
        return convertToResource(specialtyService.updateSpecialty(nutricionistId, specialtyId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/nutricionists/{nutricionistId}/specialties/{specialtyId}")
    public ResponseEntity<?> deleteSpecialty(
            @PathVariable (value = "nutricionistId") Long nutricionistId,
            @PathVariable (value = "specialtyId") Long specialtyId) {
        return specialtyService.deleteSpecialty(nutricionistId, specialtyId);
    }

    private Specialty convertToEntity(SaveSpecialtyResource resource) {
        return mapper.map(resource, Specialty.class);
    }

    private SpecialtyResource convertToResource(Specialty entity) {
        return mapper.map(entity, SpecialtyResource.class);
    }
}
