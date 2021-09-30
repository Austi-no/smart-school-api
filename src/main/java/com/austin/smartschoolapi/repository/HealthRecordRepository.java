package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRecordRepository extends JpaRepository<HealthRecord,Long> {
}
