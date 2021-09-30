package com.austin.smartschoolapi.service;

import com.austin.smartschoolapi.constants.ApiResponse;
import com.austin.smartschoolapi.constants.CustomMessages;
import com.austin.smartschoolapi.model.StudentHouse;
import com.austin.smartschoolapi.repository.StudentHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentHouseService {

    @Autowired
    private StudentHouseRepository houseRepository;


    public ResponseEntity addStudentHouse(StudentHouse house) {
        Optional<StudentHouse> optionalHouse = houseRepository.findByStudentHouse(house.getStudentHouse());
        if (optionalHouse.isPresent()) {
            house.setDateCreated(new Date());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, house.getStudentHouse() + " Already Exist!"));
        }
        house.setDateCreated(new Date());
        StudentHouse savedStudentHouse = houseRepository.save(house);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, savedStudentHouse.getStudentHouse() + " Has been Created Successfully!"));
    }

    public List<StudentHouse> getAllHouses() {
        return houseRepository.findAll();
    }

    public ResponseEntity getHouseById(Long id) {
        Optional<StudentHouse> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalHouse.get()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Not Found"));
    }

    public ResponseEntity updateStudentHouse(Long id, StudentHouse studentHouse) {
        Optional<StudentHouse> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isPresent()) {
            optionalHouse.get().setHouseCode(studentHouse.getHouseCode());
            optionalHouse.get().setStudentHouse(studentHouse.getStudentHouse());
            houseRepository.save(optionalHouse.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "Student House Updated Successfully"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Not Found"));
    }

    public ResponseEntity deleteStudentHouse(Long id) {
        Optional<StudentHouse> optionalClass = houseRepository.findById(id);
        if (optionalClass.isPresent()) {
            houseRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, "Student House Record has been Deleted Successfully"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "Student House Not Found!"));
    }
}
