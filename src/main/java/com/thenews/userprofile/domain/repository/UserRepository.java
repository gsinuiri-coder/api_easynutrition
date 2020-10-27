package com.thenews.userprofile.domain.repository;

import com.thenews.userprofile.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
