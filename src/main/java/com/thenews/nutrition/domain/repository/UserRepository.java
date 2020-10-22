package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
