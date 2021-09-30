package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
