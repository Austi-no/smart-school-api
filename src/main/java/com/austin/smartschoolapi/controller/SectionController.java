package com.austin.smartschoolapi.controller;

import com.austin.smartschoolapi.model.Section;
import com.austin.smartschoolapi.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic/section/")
public class SectionController {

    @Autowired
    private SectionService service;

    @PostMapping("add")
    public ResponseEntity addSection(@RequestBody Section section) {
        return service.addSection(section);
    }

    @GetMapping("getAll")
    public List<Section> getSections() {
        return service.getAllSections();
    }

    @GetMapping("getSection/{id}")
    public ResponseEntity getSection(@PathVariable("id") Long id) {
        return service.getSectionById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateSection(@PathVariable("id") Long id, @RequestBody Section section){
        return service.updateSection(id,section);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteSection(@PathVariable("id") Long id){
        return service.deleteSection(id);
    }

    @GetMapping("getCurrentSection")
    public ResponseEntity getCurrentSection() {
        return service.getCurrentSection();
    }

    @PostMapping("changeStatus")
    public ResponseEntity changeStatus(@RequestParam(value = "id") Long id, @RequestParam(value = "isActive") Boolean isActive) {
        return service.changeStatus(id,isActive);
    }
}
