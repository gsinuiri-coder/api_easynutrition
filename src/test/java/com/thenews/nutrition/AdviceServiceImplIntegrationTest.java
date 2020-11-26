package com.thenews.nutrition;

import com.thenews.common.exception.ResourceNotFoundException;
import com.thenews.nutrition.domain.model.Advice;
import com.thenews.nutrition.domain.service.AdviceService;
import com.thenews.nutrition.service.AdviceServiceImpl;
import com.thenews.userprofile.domain.model.Customer;
import com.thenews.userprofile.domain.model.Nutricionist;
import com.thenews.userprofile.domain.repository.CustomerRepository;
import com.thenews.userprofile.domain.repository.NutricionistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
public class AdviceServiceImplIntegrationTest {

    @Autowired
    private AdviceService adviceService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private NutricionistRepository nutricionistRepository;

    @TestConfiguration
    static class AdviceServiceImplIntegration {

        @Bean
        public AdviceService adviceService(){ return new AdviceServiceImpl();}
    }

    @Test
    @DisplayName("When createAdvice With Invalid Customer Then Throws ResourceNotFoundException")
    public void whenCreateAdviceWithInvalidCustomerThenThrowsResourceNotFoundException(){

    }
}
