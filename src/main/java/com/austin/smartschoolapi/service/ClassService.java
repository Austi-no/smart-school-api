package com.austin.smartschoolapi.service;

import com.austin.smartschoolapi.constants.ApiResponse;
import com.austin.smartschoolapi.constants.CustomMessages;
import com.austin.smartschoolapi.model.Class;
import com.austin.smartschoolapi.model.Employee;
import com.austin.smartschoolapi.repository.ClassRepository;
import com.austin.smartschoolapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public ResponseEntity addClass(Class cl) {
        Optional<Class> optionalClass = repository.findByAcademicSessionAndClassName(cl.getAcademicSession().getSession(), cl.getClassName());
        if (optionalClass.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.AlreadyExist, cl.getClassName() + " has is already available for " + cl.getAcademicSession().getSession()));
        }
        cl.setDateCreated(new Date());
        Class savedClass = repository.save(cl);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, savedClass.getClassName() + " Class has been Created! for " + savedClass.getAcademicSession().getSession(), savedClass));

    }

    public List<Class> getAllClasses() {
        return repository.findAll();
    }

    public ResponseEntity getClassById(Long id) {
        Optional<Class> optionalClass = repository.findById(id);
        if (optionalClass.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalClass));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Class Not Found!"));
    }

    public ResponseEntity updateClass(Long id, Class cl) {
        Optional<Class> optionalClass = repository.findById(id);
        if (optionalClass.isPresent()) {
            optionalClass.get().setClassName(cl.getClassName());
            optionalClass.get().getAcademicSession().setSession(cl.getAcademicSession().getSession());
            repository.save(optionalClass.get());
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Class Not Found!"));
    }

    public ResponseEntity deleteClass(Long id) {

        Optional<Class> optionalClass = repository.findById(id);
        if (optionalClass.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, "Class Record has been Deleted Successfully"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Class Not Found!"));
    }

    public ResponseEntity assignFormTeacher(Long classId, Long teacherId) {
        Optional<Class> optionalClass = repository.findById(classId);
        Optional<Employee> optionalEmployee = employeeRepository.findById(teacherId);
        if (optionalClass.isPresent() && optionalEmployee.isPresent()) {
            optionalClass.get().setFormTeacher(optionalEmployee.get());
            Class savedClass = repository.save(optionalClass.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, savedClass.getFormTeacher().getFirstName() + " " + savedClass.getFormTeacher().getLastName() + " has been assigned to " + savedClass.getClassName() + " for " + savedClass.getAcademicSession().getSession()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, CustomMessages.NotFoundMessage));
    }

    public ResponseEntity removeAssignedFormTeacher(Long id) {
        Optional<Class> optionalClass = repository.findById(id);
        if (optionalClass.isPresent()) {
            optionalClass.get().setFormTeacher(null);
            repository.save(optionalClass.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "Form Teacher Removed"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, CustomMessages.NotFoundMessage));
    }
}
