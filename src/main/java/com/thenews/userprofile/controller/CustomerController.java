package com.thenews.userprofile.controller;

import com.thenews.userprofile.domain.model.Customer;
import com.thenews.userprofile.domain.service.CustomerService;
import com.thenews.userprofile.resource.CustomerResource;
import com.thenews.userprofile.resource.SaveCustomerResource;
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
@Tag(name="Customers", description = "Customer API")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get All Customers", description = "Get All available Customers", responses = {
            @ApiResponse(
                    description = "All Customers",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/customers")
    public List<CustomerResource> getAllCustomers() {
        return customerService.getAllCustomers()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/customers/{customerId}")
    public CustomerResource getCustomerById(@PathVariable(value = "customerId") Long customerId) {
        return convertToResource(customerService.getCustomerById(customerId));
    }

    @PostMapping("/customers")
    public CustomerResource createCustomer( @Valid @RequestBody SaveCustomerResource resource) {

        Customer customer = convertToEntity(resource);
        return convertToResource(customerService.createCustomer(customer));

    }

    @PutMapping("/customers/{customerId}")
    public CustomerResource updateCustomer(@PathVariable Long customerId,
                                                   @Valid @RequestBody SaveCustomerResource resource) {
        Customer customer = convertToEntity(resource);
        return convertToResource(
                customerService.updateCustomer(customerId, customer));
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId);
    }


    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }
}
