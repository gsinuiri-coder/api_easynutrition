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

@CrossOrigin
@Tag(name = "Specialties", description = "Specialties API")
@RestController
@RequestMapping("/api")
public class SpecialtyController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SpecialtyService specialtyService;


    @Operation(summary = "Get All Specialties", description = "Get All available Specialties", responses = {
            @ApiResponse(
                    description = "All Specialties",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/specialties")
    public List<SpecialtyResource> getAllSpecialties(){
        return specialtyService.getAllSpecialties()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/specialties/{id}")
    public SpecialtyResource getSpecialtyById(@PathVariable(name = "id") Long specialtyId) {
        return convertToResource(specialtyService.getSpecialtyById(specialtyId));
    }

    @PostMapping("/specialties")
    public SpecialtyResource createSpecialty(@Valid @RequestBody SaveSpecialtyResource resource) {
        return convertToResource(specialtyService.createSpecialty(convertToEntity(resource)));
    }

    @PutMapping("/specialties/{id}")
    public SpecialtyResource updateSpecialty(@PathVariable(name = "id") Long specialtyId,
                                 SaveSpecialtyResource resource) {
        return convertToResource(specialtyService.updateSpecialty(specialtyId, convertToEntity(resource)));
    }

    @DeleteMapping("/specialties/{id}")
    public ResponseEntity<?> deleteSpecialty(@PathVariable(name = "id") Long specialtyId) {
        return specialtyService.deleteSpecialty(specialtyId);
    }

    private Specialty convertToEntity(SaveSpecialtyResource resource) {
        return mapper.map(resource, Specialty.class);
    }
    private SpecialtyResource convertToResource(Specialty entity) {
        return mapper.map(entity, SpecialtyResource.class);
    }
}
