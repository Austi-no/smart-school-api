package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.SchoolDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolDetailRepository extends JpaRepository<SchoolDetail, Long> {
    Optional<SchoolDetail> findByName(String name);
}
