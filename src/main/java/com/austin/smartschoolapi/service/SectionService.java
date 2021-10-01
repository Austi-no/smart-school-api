package com.austin.smartschoolapi.service;

import com.austin.smartschoolapi.constants.ApiResponse;
import com.austin.smartschoolapi.constants.CustomMessages;
import com.austin.smartschoolapi.model.Section;
import com.austin.smartschoolapi.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public ResponseEntity addSection(Section section) {
        Optional<Section> optionalSection = repository.findByAcademicSessionAndSection(section.getAcademicSession().getSession(), section.getSection());
        if (optionalSection.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.AlreadyExist, section.getSection() + " already exist for " + section.getAcademicSession().getSession()));
        }
        section.setIsActive(false);
        section.setDateCreated(new Date());
        Section saveSection = repository.save(section);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, saveSection.getSection() + " Created Successfully for " + saveSection.getAcademicSession().getSession()));

    }

    public List<Section> getAllSections() {
        return repository.findAll();
    }

    public ResponseEntity getSectionById(Long id) {
        Optional<Section> optionalSection = repository.findById(id);
        if (optionalSection.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalSection.get()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Section Record not Found!"));
    }

    public ResponseEntity updateSection(Long id, Section section) {
        Optional<Section> optionalSection = repository.findById(id);
        if (optionalSection.isPresent()) {
            optionalSection.get().setEndDate(section.getEndDate());
            optionalSection.get().setStartDate(section.getStartDate());
            optionalSection.get().setSection(section.getSection());
            optionalSection.get().setAcademicSession(section.getAcademicSession());

            Section updatedSection = repository.save(optionalSection.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, updatedSection.getSection() + " Section Updated!", updatedSection));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Section Record not Found!"));
    }

    public ResponseEntity deleteSection(Long id) {
        Optional<Section> optionalSection = repository.findById(id);
        if (optionalSection.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, "Section has been Deleted Successfully!"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Section Record not Found!"));
    }


    public ResponseEntity getCurrentSection() {
        List<Section> sectionList = repository.findAll();

        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, sectionList.stream().filter(x -> x.getIsActive() == true)));
    }


    public ResponseEntity changeStatus(Long id, Boolean isActive) {
        Optional<Section> optionalSection = repository.findById(id);
        if (optionalSection.isPresent()) {
            optionalSection.get().setIsActive(isActive);
            Section updatedSection = repository.save(optionalSection.get());
            if (updatedSection.getIsActive() == true) {
                return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalSection.get().getSection() + " status has been enabled!"));
            }
            if (updatedSection.getIsActive() == false) {
                return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalSection.get().getSection() + " status has been Disabled!"));
            }

        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Section was not Found!"));
    }
}
