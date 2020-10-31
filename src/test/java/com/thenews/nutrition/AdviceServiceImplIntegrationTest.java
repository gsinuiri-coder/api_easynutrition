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
        // Arrange

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUserName("cus1");
        customer.setPassword("cpass1");
        customer.setName("custoemer1");
        customer.setLastName("cls1");

        Nutricionist nutricionist = new Nutricionist();
        nutricionist.setId(2L);
        nutricionist.setUserName("nus1");
        nutricionist.setPassword("npass1");
        nutricionist.setName("nutrionist1");
        nutricionist.setLastName("nls1");

        String template = "Resource %s not found for %s with value %s";

        Mockito.when(customerRepository.save(customer))
            .thenReturn(customer);
        Mockito.when(nutricionistRepository.save(nutricionist))
            .thenReturn(nutricionist);

        Long customerId = 1L;
        Long nutricionistId = 2L;

        Advice advice = new Advice();
        advice.setActive(true);

        String expectedMessage = String.format(template, "Customer", "Id", 1L);

        // Act
        Throwable exception = catchThrowable(() -> {
            Advice createAdvice = adviceService.createAdvice(customerId, nutricionistId, advice);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
