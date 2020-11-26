package com.thenews.nutrition.contoller;

import com.thenews.nutrition.domain.model.Progress;
import com.thenews.nutrition.domain.model.Progress;
import com.thenews.nutrition.domain.service.ProgressService;
import com.thenews.nutrition.domain.service.ProgressService;
import com.thenews.nutrition.resource.ProgressResource;
import com.thenews.nutrition.resource.SaveProgressResource;
import com.thenews.nutrition.resource.SaveProgressResource;
import com.thenews.nutrition.resource.ProgressResource;
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

@Tag(name="Progress", description = "Progress API")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Progress", description = "Get All available Progress", responses = {
            @ApiResponse(
                    description = "All Progress",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/advices/{adviceId}/progresss")
    public Page<ProgressResource> getAllProgresssByAdviceId(
            @PathVariable (value = "adviceId") Long adviceId,
            Pageable pageable) {
        Page<Progress> progressPage = progressService.getAllProgresssByAdviceId(adviceId, pageable);
        List<ProgressResource> resources = progressPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/advices/{adviceId}/progresss/{progressId}")
    public ProgressResource getProgressByIdAndAdviceId(
            @PathVariable(name = "adviceId") Long adviceId,
            @PathVariable(name = "progressId") Long progressId) {
        return convertToResource(progressService.getProgressByIdAndAdviceId(adviceId, progressId));
    }

    @PostMapping("/advices/{adviceId}/progresss")
    public ProgressResource createProgress(
            @PathVariable(value = "adviceId") Long adviceId,
            @Valid @RequestBody SaveProgressResource resource) {
        return convertToResource(progressService.createProgress(adviceId,
                convertToEntity(resource)));
    }

    @PutMapping("/advices/{adviceId}/progresss/{progressId}")
    public ProgressResource updateProgress(
            @PathVariable (value = "adviceId") Long adviceId,
            @PathVariable (value = "progressId") Long progressId,
            @Valid @RequestBody SaveProgressResource resource) {
        return convertToResource(progressService.updateProgress(adviceId, progressId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/advices/{adviceId}/progresss/{progressId}")
    public ResponseEntity<?> deleteProgress(
            @PathVariable (value = "adviceId") Long adviceId,
            @PathVariable (value = "progressId") Long progressId) {
        return progressService.deleteProgress(adviceId, progressId);
    }

    private Progress convertToEntity(SaveProgressResource resource) {
        return mapper.map(resource, Progress.class);
    }

    private ProgressResource convertToResource(Progress entity) {
        return mapper.map(entity, ProgressResource.class);
    }
}
