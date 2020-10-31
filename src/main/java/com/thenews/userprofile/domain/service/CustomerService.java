package com.thenews.userprofile.domain.service;

import com.thenews.userprofile.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    Page<Customer> getAllCustomers(Pageable pageable);
    Customer getCustomerById(Long customerId);

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customerRequest);
    ResponseEntity<?> deleteCustomer(Long customerId);
}
