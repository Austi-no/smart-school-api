package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
