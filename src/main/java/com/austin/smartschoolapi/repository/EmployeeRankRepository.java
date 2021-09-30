package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.EmployeeRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRankRepository extends JpaRepository<EmployeeRank,Long> {
}
