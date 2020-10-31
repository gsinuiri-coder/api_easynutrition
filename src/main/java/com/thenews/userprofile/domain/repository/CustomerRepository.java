package com.thenews.userprofile.domain.repository;

import com.thenews.userprofile.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
