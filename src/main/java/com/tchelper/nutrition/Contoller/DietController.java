package com.tchelper.nutrition.Contoller;

import com.tchelper.nutrition.domain.model.Diet;
import com.tchelper.nutrition.domain.service.DietService;
import com.tchelper.nutrition.resource.SaveDietResource;
import com.tchelper.nutrition.resource.DietResource;
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

@Tag(name="sessions", description = "Sessions API")
@RestController
@RequestMapping("/api")
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/diets")
    public Page<DietResource> getAllDiets(Pageable pageable) {

        Page<Diet> dietsPage = dietService.getAllDiets(pageable);
        List<DietResource> resources = dietsPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/sessions/{sessionId}/diets/{dietId}")
    public DietResource getDietByIdAndPostId(
            @PathVariable(name = "sessionId") Long postId,
            @PathVariable(name = "dietId") Long dietId) {
        return convertToResource(dietService.getDietByIdAndSessionId(postId, dietId));
    }


    @GetMapping("/diets/{dietId}")
    public DietResource getDietById(@PathVariable (value = "dietId") Long dietId) {
        return convertToResource(dietService.getDietById(dietId));
    }

    @PostMapping("/diets")
    public DietResource createDiet(
            @Valid @RequestBody SaveDietResource resource) {
        Diet diet = convertToEntity(resource);
        return convertToResource(dietService.createDiet(diet));

    }

    @PutMapping("/diets/{dietId}")
    public DietResource updateDiet(@PathVariable Long dietId,
                                         @Valid @RequestBody SaveDietResource resource) {
        Diet diet = convertToEntity(resource);
        return convertToResource(
                dietService.updateDiet(dietId, diet));
    }

    @DeleteMapping("/diets/{dietId}")
    public ResponseEntity<?> deleteDiet(@PathVariable Long dietId) {
        return dietService.deleteDiet(dietId);
    }

    private Diet convertToEntity(SaveDietResource resource) {
        return mapper.map(resource, Diet.class);
    }

    private DietResource convertToResource(Diet entity) {
        return mapper.map(entity, DietResource.class);
    }
}
