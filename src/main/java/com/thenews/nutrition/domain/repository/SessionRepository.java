package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
