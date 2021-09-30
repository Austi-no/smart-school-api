package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {

    Optional<Class> findByAcademicSessionAndClassName(String session, String className);
}
