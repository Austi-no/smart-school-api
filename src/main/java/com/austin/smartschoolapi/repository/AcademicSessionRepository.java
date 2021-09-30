package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.AcademicSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademicSessionRepository extends JpaRepository<AcademicSession,Long> {
    Optional<AcademicSession> findBySession(String session);
}
