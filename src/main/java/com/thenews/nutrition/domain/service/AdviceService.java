package com.thenews.nutrition.domain.service;

import com.thenews.nutrition.domain.model.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdviceService {
//    Page<Advice> getAllAdvices(Pageable pageable);
//    Advice getAdviceById(Long adviceId);
//    Advice createAdvice(Advice advice);
//    Advice updateAdvice(Long adviceId, Advice adviceRequest);
//    ResponseEntity<?> deleteAdvice(Long adviceId);

//    Page<Advice> getAllAdvicesByCustomerId(Long customerId, Pageable pageable);
//    Advice getAdviceByIdAndCustomerId(Long customerId, Long commentId);
    Advice createAdvice(Long customerId, Long nutricionistId, Advice advice);

//    Advice createAdvice(Advice advice);

//    Advice updateAdvice(Long customerId, Long commentId, Advice commentDetails);
//    ResponseEntity<?> deleteAdvice(Long customerId, Long commentId);

    Advice assignAdviceDiet(Long adviceId, Long dietId);
    Advice unassignAdviceDiet(Long adviceId, Long dietId);
    Page<Advice> getAllAdvicesByDietId(Long dietId, Pageable pageable);
//    Advice getAdviceByTitle(String title);



}
