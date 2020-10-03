package com.tchelper.nutrition.domain.repository;

import com.tchelper.nutrition.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
