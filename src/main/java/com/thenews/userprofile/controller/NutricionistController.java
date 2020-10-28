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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="Nutricionists", description = "Nutricionist API")
@RestController
@RequestMapping("/api")
public class NutricionistController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private NutricionistService nutricionistService;

    @GetMapping("/nutricionists")
    public Page<NutricionistResource> getAllNutricionists(Pageable pageable) {

        Page<Nutricionist> nutricionistsPage = nutricionistService.getAllNutricionists(pageable);
        List<NutricionistResource> resources = nutricionistsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/nutricionists/{nutricionistId}")
    public NutricionistResource getNutricionistById(@PathVariable(value = "nutricionistId") Long nutricionistId) {
        return convertToResource(nutricionistService.getNutricionistById(nutricionistId));
    }

    @PostMapping("/nutricionists")
    public NutricionistResource createNutricionist( @Valid @RequestBody SaveNutricionistResource resource) {

        Nutricionist nutricionist = convertToEntity(resource);
        return convertToResource(nutricionistService.createNutricionist(nutricionist));

    }

    @PutMapping("/nutricionists/{nutricionistId}")
    public NutricionistResource updateNutricionist(@PathVariable Long nutricionistId,
                                   @Valid @RequestBody SaveNutricionistResource resource) {
        Nutricionist nutricionist = convertToEntity(resource);
        return convertToResource(
                nutricionistService.updateNutricionist(nutricionistId, nutricionist));
    }

    @DeleteMapping("/nutricionists/{nutricionistId}")
    public ResponseEntity<?> deleteNutricionist(@PathVariable Long nutricionistId) {
        return nutricionistService.deleteNutricionist(nutricionistId);
    }


    private Nutricionist convertToEntity(SaveNutricionistResource resource) {
        return mapper.map(resource, Nutricionist.class);
    }

    private NutricionistResource convertToResource(Nutricionist entity) {
        return mapper.map(entity, NutricionistResource.class);
    }
}
