package com.thenews.nutrition.contoller;

import com.thenews.nutrition.domain.model.Progress;
import com.thenews.nutrition.domain.service.ProgressService;
import com.thenews.nutrition.resource.ProgressResource;
import com.thenews.nutrition.resource.SaveProgressResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name="Progress", description = "Progress API")
@RestController
@RequestMapping("/api")
public class ProgressController {


    @Autowired
    private ProgressService progressService;

    @Autowired
    private ModelMapper mapper;
    
    @GetMapping("/sessions/{sessionId}/progresss/{progressId}")
    public ProgressResource getProgressByIdAndPostId(
            @PathVariable(name = "sessionId") Long sessionId,
            @PathVariable(name = "progressId") Long progressId) {
        return convertToResource(progressService.getProgressByIdAndSessionId(sessionId, progressId));
    }

    @PostMapping("/sessions/{sessionId}/progresss")
    public ProgressResource createProgress(
            @PathVariable(value = "sessionId") Long sessionId,
            @Valid @RequestBody SaveProgressResource resource) {
        return convertToResource(progressService.createProgress(sessionId,
                convertToEntity(resource)));
    }

    @PutMapping("/sessions/{sessionId}/progresss/{progressId}")
    public ProgressResource updateProgress(
            @PathVariable (value = "sessionId") Long sessionId,
            @PathVariable (value = "progressId") Long progressId,
            @Valid @RequestBody SaveProgressResource resource) {
        return convertToResource(progressService.updateProgress(sessionId, progressId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/sessions/{sessionId}/progresss/{progressId}")
    public ResponseEntity<?> deleteProgress(
            @PathVariable (value = "sessionId") Long sessionId,
            @PathVariable (value = "progressId") Long progressId) {
        return progressService.deleteProgress(sessionId, progressId);
    }
    private Progress convertToEntity(SaveProgressResource resource) {
        return mapper.map(resource, Progress.class);
    }

    private ProgressResource convertToResource(Progress entity) {
        return mapper.map(entity, ProgressResource.class);
    }
}
