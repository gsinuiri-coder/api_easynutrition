package com.thenews.nutrition.contoller;

import com.thenews.nutrition.domain.model.Session;
import com.thenews.nutrition.domain.service.SessionService;
import com.thenews.nutrition.resource.SaveSessionResource;
import com.thenews.nutrition.resource.SessionResource;
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
@Tag(name="Sessions", description = "Sessions API")
@RestController
@RequestMapping("/api")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Sessions", description = "Get All available Sessions", responses = {
            @ApiResponse(
                    description = "All Sessions",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/advices/{adviceId}/sessions")
    public List<SessionResource> getAllSessionsByAdviceId(@PathVariable (value = "adviceId") Long adviceId) {
        return sessionService.getAllSessionsByAdviceId(adviceId)
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/advices/{adviceId}/sessions/{sessionId}")
    public SessionResource getSessionByIdAndAdviceId(
            @PathVariable(name = "adviceId") Long adviceId,
            @PathVariable(name = "sessionId") Long sessionId) {
        return convertToResource(sessionService.getSessionByIdAndAdviceId(adviceId, sessionId));
    }

    @PostMapping("/advices/{adviceId}/sessions")
    public SessionResource createSession(
            @PathVariable(value = "adviceId") Long adviceId,
            @Valid @RequestBody SaveSessionResource resource) {
        return convertToResource(sessionService.createSession(adviceId,
                convertToEntity(resource)));
    }

    @PutMapping("/advices/{adviceId}/sessions/{sessionId}")
    public SessionResource updateSession(
            @PathVariable (value = "adviceId") Long adviceId,
            @PathVariable (value = "sessionId") Long sessionId,
            @Valid @RequestBody SaveSessionResource resource) {
        return convertToResource(sessionService.updateSession(adviceId, sessionId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/advices/{adviceId}/sessions/{sessionId}")
    public ResponseEntity<?> deleteSession(
            @PathVariable (value = "adviceId") Long adviceId,
            @PathVariable (value = "sessionId") Long sessionId) {
        return sessionService.deleteSession(adviceId, sessionId);
    }

    private Session convertToEntity(SaveSessionResource resource) {
        return mapper.map(resource, Session.class);
    }
    private SessionResource convertToResource(Session entity) {
        return mapper.map(entity, SessionResource.class);
    }
}
