package com.thenews.nutrition.contoller;

import com.thenews.nutrition.domain.model.Score;
import com.thenews.nutrition.domain.service.ScoreService;
import com.thenews.nutrition.resource.SaveScoreResource;
import com.thenews.nutrition.resource.ScoreResource;
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

@Tag(name="Scores", description = "Scores API")
@RestController
@RequestMapping("/api")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ModelMapper mapper;

//    @Operation(summary = "Get All Scores", description = "Get All available Scores", responses = {
//            @ApiResponse(
//                    description = "All Scores",
//                    responseCode = "200",
//                    content = @Content(mediaType = "application/json"))
//    })
//    @GetMapping("/advices/{adviceId}/scores")
//    public Page<ScoreResource> getAllScoresByAdviceId(
//            @PathVariable(value = "adviceId") Long adviceId,
//            Pageable pageable) {
//        Page<Score> scorePage = scoreService.getAllScoresByAdviceId(adviceId, pageable);
//        List<ScoreResource> resources = scorePage.getContent().stream()
//                .map(this::convertToResource).collect(Collectors.toList());
//        return new PageImpl<>(resources, pageable, resources.size());
//    }
//
//    @GetMapping("/advices/{adviceId}/scores/{scoreId}")
//    public ScoreResource getScoreByIdAndAdviceId(
//            @PathVariable(name = "adviceId") Long adviceId,
//            @PathVariable(name = "scoreId") Long scoreId) {
//        return convertToResource(scoreService.getScoreByIdAndAdviceId(adviceId, scoreId));
//    }

    @Operation(summary = "Get Score", description = "Get a Score", responses = {@ApiResponse(
            description = "Get Score",
            responseCode = "200",
            content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/advices/{adviceId}/scores")
    public ScoreResource getScoreByAdviceId(
            @PathVariable(name = "adviceId") Long adviceId) {
        return convertToResource(scoreService.getScoreByAdviceId(adviceId));
    }

    @PostMapping("/advices/{adviceId}/scores")
    public ScoreResource createScore(
            @PathVariable(value = "adviceId") Long adviceId,
            @Valid @RequestBody SaveScoreResource resource) {
        return convertToResource(scoreService.createScore(adviceId,
                convertToEntity(resource)));
    }

    @PutMapping("/advices/{adviceId}/scores/{scoreId}")
    public ScoreResource updateScore(
            @PathVariable (value = "adviceId") Long adviceId,
            @PathVariable (value = "scoreId") Long scoreId,
            @Valid @RequestBody SaveScoreResource resource) {
        return convertToResource(scoreService.updateScore(adviceId, scoreId,
                convertToEntity(resource)));
    }

    @DeleteMapping("/advices/{adviceId}/scores/{scoreId}")
    public ResponseEntity<?> deleteScore(
            @PathVariable (value = "adviceId") Long adviceId,
            @PathVariable (value = "scoreId") Long scoreId) {
        return scoreService.deleteScore(adviceId, scoreId);
    }

    private Score convertToEntity(SaveScoreResource resource) {
        return mapper.map(resource, Score.class);
    }

    private ScoreResource convertToResource(Score entity) {
        return mapper.map(entity, ScoreResource.class);
    }
}
