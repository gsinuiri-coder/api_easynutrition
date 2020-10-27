package com.thenews.userprofile.domain.repository;

import com.thenews.userprofile.domain.model.Nutricionist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutricionistRepository extends JpaRepository<Nutricionist, Long> {
}
