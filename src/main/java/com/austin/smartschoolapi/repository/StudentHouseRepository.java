package com.austin.smartschoolapi.repository;

import com.austin.smartschoolapi.model.StudentHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentHouseRepository extends JpaRepository<StudentHouse,Long> {

    Optional<StudentHouse> findByStudentHouse(String house);
}
