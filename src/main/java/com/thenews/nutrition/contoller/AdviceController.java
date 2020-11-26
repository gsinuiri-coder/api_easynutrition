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

@Tag(name="Advices", description = "Advices API")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdviceController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AdviceService adviceService;

//    @Operation(summary = "Get All Advices", description = "Get All available Advices", responses = {
//            @ApiResponse(
//                    description = "All Advices",
//                    responseCode = "200",
//                    content = @Content(mediaType = "application/json"))
//    })
//    @GetMapping("/advices")
//    public Page<AdviceResource> getAllAdvices(Pageable pageable) {
//
//        Page<Advice> advicesPage = adviceService.getAllAdvices(pageable);
//        List<AdviceResource> resources = advicesPage.getContent()
//                .stream().map(this::convertToResource)
//                .collect(Collectors.toList());
//        return new PageImpl<>(resources, pageable, resources.size());
//    }
//
//    @GetMapping("/advices/{adviceId}")
//    public AdviceResource getAdviceById(@PathVariable(value = "adviceId") Long adviceId) {
//        return convertToResource(adviceService.getAdviceById(adviceId));
//    }

    @PostMapping("/advices/customers/{customerId}/nutricionists/{nutricionistId}")
    public AdviceResource createAdvice(
            @PathVariable(value = "customerId") Long customerId,
            @PathVariable(value = "nutricionistId") Long nutricionistId,
            @Valid @RequestBody SaveAdviceResource resource) {
        Advice advice = convertToEntity(resource);
        return convertToResource(adviceService.createAdvice(customerId, nutricionistId, advice));
    }

//    @PutMapping("/advices/{adviceId}")
//    public AdviceResource updateAdvice(@PathVariable Long adviceId,
//                                   @Valid @RequestBody SaveAdviceResource resource) {
//        Advice advice = convertToEntity(resource);
//        return convertToResource(
//                adviceService.updateAdvice(adviceId, advice));
//    }
//
//    @DeleteMapping("/advices/{adviceId}")
//    public ResponseEntity<?> deleteAdvice(@PathVariable Long adviceId) {
//        return adviceService.deleteAdvice(adviceId);
//    }
//

    private Advice convertToEntity(SaveAdviceResource resource) {
        return mapper.map(resource, Advice.class);
    }

    private AdviceResource convertToResource(Advice entity) {
        return mapper.map(entity, AdviceResource.class);
    }

}
