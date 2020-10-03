package com.tchelper.EasyNutrition.Contoller;

import com.tchelper.EasyNutrition.domain.model.Session;
import com.tchelper.EasyNutrition.domain.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/sessions")
    public Page<Session> getAllSessions(Pageable pageable){
        return sessionRepository.findAll(pageable);
    }
}
