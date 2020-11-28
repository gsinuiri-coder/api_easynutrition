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
    private CustomerRepository customerRepository;

    @Autowired
    private DietRepository dietRepository;

    @Override
    public List<Advice> getAllAdvicesByCustomerId(Long customerId) {
        return adviceRepository.findByCustomerId(customerId);
    }

    @Override
    public Advice getAdviceByIdAndCustomerId(Long customerId, Long adviceId) {
        return adviceRepository.findByIdAndCustomerId(adviceId, customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Advice not found with Id " + adviceId +
                                " and CustomerId " + customerId));
    }

    @Override
    public Advice createAdvice(Long customerId, Advice advice) {
        return customerRepository.findById(customerId).map(customer -> {
            advice.setCustomer(customer);
            return adviceRepository.save(advice);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Customer", "Id", customerId));
    }

    @Override
    public Advice updateAdvice(Long customerId, Long adviceId, Advice adviceDetails) {
        if(!customerRepository.existsById(customerId))
            throw new ResourceNotFoundException("Customer", "Id", customerId);

        return adviceRepository.findById(adviceId).map(advice -> {
            advice.setActive(adviceDetails.isActive());
            return adviceRepository.save(advice);
        }).orElseThrow(() -> new ResourceNotFoundException("Advice", "Id", adviceId));
    }

    @Override
    public ResponseEntity<?> deleteAdvice(Long customerId, Long adviceId) {
        return adviceRepository.findByIdAndCustomerId(adviceId, customerId).map(advice -> {
            adviceRepository.delete(advice);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Advice not found with Id " + adviceId + " and CustomerId " + customerId));
    }

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
    public List<Advice> getAllAdvicesByDietId(Long dietId) {
        return dietRepository.findById(dietId).map(Diet::getAdvices)
                .orElseThrow(() -> new ResourceNotFoundException("Diet", "Id", dietId));
    }
}
