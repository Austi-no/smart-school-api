package com.austin.smartschoolapi.controller;

import com.austin.smartschoolapi.model.Subject;
import com.austin.smartschoolapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject/")
public class SubjectController {

    @Autowired
    private SubjectService service;

    @PostMapping("add")
    public ResponseEntity addSubject(@RequestBody Subject subject) {
        return service.addSubject(subject);
    }

    @GetMapping("getAll")
    public List<Subject> getSubjects() {
        return service.getStudents();
    }

    @GetMapping("getSubject/{id}")
    public ResponseEntity getStudent(@PathVariable("id") Long id) {
        return service.getStudent(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateSubject(@PathVariable("id") Long id, @RequestBody Subject subject) {
        return service.updateSubject(id, subject);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteSubject(@PathVariable("id") Long id) {
        return service.deleteSubject(id);
    }

    @PostMapping("assignTeacher/{subjectId}/{teacherId}")
    public ResponseEntity assignTeacher(@PathVariable("subjectId") Long subjectId, @PathVariable("teacherId") Long teacherId) {
        return service.assignSubjectTeacher(subjectId, teacherId);
    }

    @PutMapping("removeTeacher/{id}")
    public ResponseEntity removeSubjectTeacher(@PathVariable("id") Long id) {
        return service.removeSubjectTeacher(id);
    }
}
