package com.thenews.nutrition.service;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.nutrition.domain.model.Advice;
import com.thenews.nutrition.domain.model.Diet;
import com.thenews.nutrition.domain.repository.AdviceRepository;
import com.thenews.nutrition.domain.repository.DietRepository;
import com.thenews.nutrition.domain.service.AdviceService;
import com.thenews.userprofile.domain.model.Customer;
import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.repository.CustomerRepository;
import com.thenews.userprofile.domain.repository.NutricionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceRepository adviceRepository;

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NutricionistRepository nutricionistRepository;

//    @Override
//    public Page<Advice> getAllAdvices(Pageable pageable) {
//        return adviceRepository.findAll(pageable);
//    }
//
//    @Override
//    public Advice getAdviceById(Long adviceId) {
//        return adviceRepository.findById(adviceId)
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        "Advice", "Id", adviceId));
//    }

    @Override
    public Advice createAdvice(Long customerId, Long nutricionistId, Advice advice) {

        Customer customerTemp = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer", "Id", customerId));

        if (customerTemp != null){
            advice.setCustomer(customerTemp);
            Nutricionist nutricionistTemp = nutricionistRepository.findById(nutricionistId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Nutricionist", "Id", nutricionistId));
            if (nutricionistTemp != null){
                advice.setNutricionist(nutricionistTemp);
                return adviceRepository.save(advice);
            }
        }
        return null;
    }

//    @Override
//    public Advice createAdvice(Advice advice) { return adviceRepository.save(advice); }

//    @Override
//    public Advice updateAdvice(Long adviceId, Advice adviceRequest) {
//        Advice advice = adviceRepository.findById(adviceId)
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        "Advice", "Id", adviceId));
//        return adviceRepository.save(
//                advice.setActive(adviceRequest.isActive())
//        );
//    }
//
//    @Override
//    public ResponseEntity<?> deleteAdvice(Long adviceId) {
//        Advice advice = adviceRepository.findById(adviceId)
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        "Advice", "Id", adviceId));
//        adviceRepository.delete(advice);
//        return ResponseEntity.ok().build();
//    }

    @Override
    public Advice assignAdviceDiet(Long adviceId, Long dietId) {
        Diet diet = dietRepository.findById(dietId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Diet", "Id", dietId));
        return adviceRepository.findById(adviceId).map(advice -> {
            return adviceRepository.save(advice.tagWith(diet));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Advice", "Id", adviceId));

    }

    @Override
    public Advice unassignAdviceDiet(Long adviceId, Long dietId) {
        Diet diet = dietRepository.findById(dietId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Diet", "Id", dietId));
        return adviceRepository.findById(adviceId).map(advice -> {
            return adviceRepository.save(advice.unTagWith(diet));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Advice", "Id", adviceId));
    }

    @Override
    public Page<Advice> getAllAdvicesByDietId(Long dietId, Pageable pageable) {
        return dietRepository.findById(dietId).map( diet -> {
            List<Advice> advices = diet.getAdvices();
            int advicesCount = advices.size();
            return new PageImpl<>(advices, pageable, advicesCount);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Diet", "Id", dietId));
    }
}
