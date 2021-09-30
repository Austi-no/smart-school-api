package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.EmployeeQualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeQualificationRepository extends JpaRepository<EmployeeQualification,Long> {
}
