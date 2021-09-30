package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByAcademicSessionAndSection(String session, String section);
}
