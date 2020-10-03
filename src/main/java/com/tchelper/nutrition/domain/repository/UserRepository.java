package com.tchelper.nutrition.domain.repository;

import com.tchelper.nutrition.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
