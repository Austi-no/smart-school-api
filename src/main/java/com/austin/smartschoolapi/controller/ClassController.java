package com.austin.smartschoolapi.controller;

import com.austin.smartschoolapi.model.Class;
import com.austin.smartschoolapi.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class/")
public class ClassController {

    @Autowired
    private ClassService service;

    @PostMapping("add")
    public ResponseEntity addClass(@RequestBody Class cl) {
        return service.addClass(cl);
    }

    @GetMapping("getAll")
    public List<Class> getAllClasses() {
        return service.getAllClasses();
    }

    @GetMapping("{id}")
    public ResponseEntity getClassById(@PathVariable("id") Long id) {
        return service.getClassById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateClass(@PathVariable("id") Long id, @RequestBody Class cl) {
        return service.updateClass(id, cl);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteClass(@PathVariable("id") Long id) {
        return service.deleteClass(id);
    }

    @PostMapping("assignTeacher/{classId}/{teacherId}")
    public ResponseEntity assignTeacher(@PathVariable("classId") Long classId, @PathVariable("teacherId") Long teacherId) {
        return service.assignFormTeacher(classId, teacherId);
    }

    @PutMapping("removeAssignTeacher/{id}")
    public ResponseEntity removeAssignTeacher(@PathVariable("id") Long id) {
        return service.removeAssignedFormTeacher(id);
    }


}
