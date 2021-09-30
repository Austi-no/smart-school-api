package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository extends JpaRepository<Guardian,Long> {
}
