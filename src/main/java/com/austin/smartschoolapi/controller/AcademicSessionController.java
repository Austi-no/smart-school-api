package com.austin.smartschoolapi.controller;

import com.austin.smartschoolapi.model.AcademicSession;
import com.austin.smartschoolapi.service.AcademicSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic/session/")
public class AcademicSessionController {

    @Autowired
    private AcademicSessionService sessionService;

    @PostMapping("add")
    public ResponseEntity addSession(@RequestBody AcademicSession session) {
        return sessionService.addSession(session);
    }

    @GetMapping("all")
    public List<AcademicSession> getAllSession() {
        return sessionService.getSessions();
    }

    @GetMapping("{id}")
    public ResponseEntity getSessionById(@PathVariable("id") Long id) {
        return sessionService.getSessionById(id);
    }

    @PostMapping("update/{id}")
    public ResponseEntity updateSession(@PathVariable("id") Long id, AcademicSession session) {
        return sessionService.updateSession(id, session);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteSession(@PathVariable("id") Long id) {
        return sessionService.deleteSession(id);
    }

    @GetMapping("getCurrentSession")
    public ResponseEntity getCurrentSession() {
        return sessionService.getCurrentSession();
    }

    @PostMapping("changeStatus")
    public ResponseEntity changeStatus(@RequestParam(value = "sessionId") Long sessionId, @RequestParam(value = "isActive") Boolean isActive) {
        return sessionService.changeStatus(sessionId,isActive);
    }

}
