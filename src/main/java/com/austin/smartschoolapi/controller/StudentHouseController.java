package com.austin.smartschoolapi.controller;

import com.austin.smartschoolapi.model.StudentHouse;
import com.austin.smartschoolapi.service.StudentHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/house/")
public class StudentHouseController {

    @Autowired
    private StudentHouseService service;

    @PostMapping("add")
    public ResponseEntity addStudentHouse(@RequestBody StudentHouse house) {
        return service.addStudentHouse(house);
    }

    @GetMapping("all")
    public List<StudentHouse> getStudentHouses() {
        return service.getAllHouses();
    }

    @GetMapping("{id}")
    public ResponseEntity getStudentHouseById(@PathVariable("id") Long id) {
        return service.getHouseById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateStudentHouse(@PathVariable("id") Long id, @RequestBody StudentHouse studentHouse) {
        return service.updateStudentHouse(id, studentHouse);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteStudentHouse(@PathVariable("id") Long id) {
        return service.deleteStudentHouse(id);
    }

}
