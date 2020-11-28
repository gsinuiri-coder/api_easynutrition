package com.thenews.nutrition.contoller;

import com.thenews.nutrition.domain.model.Advice;
import com.thenews.nutrition.domain.service.AdviceService;
import com.thenews.nutrition.resource.AdviceResource;
import com.thenews.nutrition.resource.SaveAdviceResource;
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
@Tag(name="Advices", description = "Advices API")
@RestController
@RequestMapping("/api")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/customers/{customerId}/advices")
    public List<AdviceResource> getAllAdvicesByCustomerId(@PathVariable (value = "customerId") Long customerId) {
        return adviceService.getAllAdvicesByCustomerId(customerId)
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/customers/{customerId}/advices/{adviceId}")
    public AdviceResource getAdviceByIdAndCustomerId(
            @PathVariable(name = "customerId") Long customerId,
            @PathVariable(name = "adviceId") Long adviceId) {
        return convertToResource(adviceService.getAdviceByIdAndCustomerId(customerId, adviceId));
    }

    @PostMapping("/customers/{customerId}/advices")
    public AdviceResource createAdvice(
            @PathVariable(value = "customerId") Long customerId,
            @Valid @RequestBody SaveAdviceResource resource) {
        return convertToResource(adviceService.createAdvice(customerId,
                convertToEntity(resource)));
    }

    @PutMapping("/customers/{customerId}/advices/{adviceId}")
    public AdviceResource updateAdvice(
            @PathVariable (value = "customerId") Long customerId,
            @PathVariable (value = "adviceId") Long adviceId,
            @Valid @RequestBody SaveAdviceResource resource) {
        return convertToResource(adviceService.updateAdvice(customerId, adviceId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/customers/{customerId}/advices/{adviceId}")
    public ResponseEntity<?> deleteAdvice(
            @PathVariable (value = "customerId") Long customerId,
            @PathVariable (value = "adviceId") Long adviceId) {
        return adviceService.deleteAdvice(customerId, adviceId);
    }

    private Advice convertToEntity(SaveAdviceResource resource) {
        return mapper.map(resource, Advice.class);
    }
    private AdviceResource convertToResource(Advice entity) {
        return mapper.map(entity, AdviceResource.class);
    }
}
