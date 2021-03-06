package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Optional<Subject> findBySubject(String subject);
}
