package com.austin.smartschoolapi.controller;

import com.austin.smartschoolapi.model.SchoolDetail;
import com.austin.smartschoolapi.service.SchoolDetailService;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/schoolDetail/")
public class SchoolDetailController implements Serializable {

    @Autowired
    private SchoolDetailService service;

    @ApiOperation("Add new School Detail")
    @PostMapping("add")
    public ResponseEntity addSchoolDetail(@Valid @RequestBody SchoolDetail school) {
        return service.addSchool(school);
    }

    @ApiOperation("Get All School Detail")
    @GetMapping("get")
    public List<SchoolDetail> getDetail() {
        return service.getSchoolDetail();
    }

    @ApiOperation("Get School Detail By Id")
    @GetMapping("get/{id}")
    public ResponseEntity getSchoolDetailById(@PathVariable("id") Long id) {
        return service.getSchoolDetailById(id);
    }

    @ApiOperation("Edit School Detail")
    @PostMapping("edit/{id}")
    public ResponseEntity editSchoolDetail(@Valid @RequestBody SchoolDetail school, @PathVariable("id") Long id) {
        return service.editSchoolDetailById(id, school);
    }

    @ApiOperation("Delete School Detail")
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteSchool(@PathVariable("id") Long id) {
        return service.deleteSchoolById(id);
    }


}
