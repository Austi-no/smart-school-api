package com.austin.smartschoolapi.service;

import com.austin.smartschoolapi.constants.ApiResponse;
import com.austin.smartschoolapi.constants.CustomMessages;
import com.austin.smartschoolapi.model.Employee;
import com.austin.smartschoolapi.model.Subject;
import com.austin.smartschoolapi.repository.EmployeeRepository;
import com.austin.smartschoolapi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    @Autowired
    private EmployeeRepository teacherRepository;

    public ResponseEntity addSubject(Subject subject) {
        Optional<Subject> optionalSubject = repository.findBySubject(subject.getSubject());
        if (optionalSubject.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, subject.getSubject() + " already Exist!"));
        }
        subject.setDateCreated(new Date());
        Subject savedSubject = repository.save(subject);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, savedSubject.getSubject() + " has been Added Successfully!"));
    }

    public List<Subject> getStudents() {
        return repository.findAll();
    }

    public ResponseEntity getStudent(Long id) {
        Optional<Subject> optionalSubject = repository.findById(id);
        if (optionalSubject.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalSubject.get()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Subject Not Found!"));
    }

    public ResponseEntity updateSubject(Long id, Subject subject) {
        Optional<Subject> optionalSubject = repository.findById(id);
        if (optionalSubject.isPresent()) {
            optionalSubject.get().setSubject(subject.getSubject());
            optionalSubject.get().setSubjectType(subject.getSubjectType());
            Subject updatedSubject = repository.save(optionalSubject.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, updatedSubject.getSubject() + " updated Successfully!"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Subject Not Found!"));
    }

    public ResponseEntity deleteSubject(Long id) {
        Optional<Subject> optionalSubject = repository.findById(id);
        if (optionalSubject.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, "Subject Record has been Deleted Successfully"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Subject Not Found!"));

    }

    public ResponseEntity assignSubjectTeacher(Long subjectId, Long teacherId) {
        Optional<Subject> optionalSubject = repository.findById(subjectId);
        Optional<Employee> optionalTeacher = teacherRepository.findById(teacherId);

        if (optionalSubject.isPresent() && optionalTeacher.isPresent()) {
            optionalSubject.get().setSubjectTeacher(optionalTeacher.get());
            Subject updatedSubject = repository.save(optionalSubject.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, updatedSubject.getSubjectTeacher().getFirstName() + " " + updatedSubject.getSubjectTeacher().getLastName() + " has been assigned to " + updatedSubject.getSubject()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Subject Not Found!"));
    }

    public ResponseEntity removeSubjectTeacher(Long id) {
        Optional<Subject> optionalSubject = repository.findById(id);
        if (optionalSubject.isPresent()) {
            optionalSubject.get().setSubjectTeacher(null);
            repository.save(optionalSubject.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "Subject Teacher Removed!"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Subject Not Found!"));
    }
}
