package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdviceService {

    List<Advice> getAllAdvicesByCustomerId(Long customerId);
    Advice getAdviceByIdAndCustomerId(Long customerId, Long adviceId);
    Advice createAdvice(Long customerId, Advice advice);
    Advice updateAdvice(Long customerId, Long adviceId, Advice adviceDetails);
    ResponseEntity<?> deleteAdvice(Long customerId, Long adviceId);

    Advice assignAdviceDiet(Long adviceId, Long dietId);
    Advice unassignAdviceDiet(Long adviceId, Long dietId);
    List<Advice> getAllAdvicesByDietId(Long dietId);
}
