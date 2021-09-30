package com.austin.smartschoolapi.service;

import com.austin.smartschoolapi.constants.ApiResponse;
import com.austin.smartschoolapi.constants.CustomMessages;
import com.austin.smartschoolapi.model.SchoolDetail;
import com.austin.smartschoolapi.repository.SchoolDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolDetailService {
    @Autowired
    private SchoolDetailRepository repository;

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }

    //ADD SCHOOL DETAIL
    public ResponseEntity addSchool(SchoolDetail school) {
        school.setSchoolCode(school.getSchoolCode().toUpperCase());
        school.setEmail(school.getEmail().toLowerCase());
        school.setYoutubeURL(school.getYoutubeURL().toLowerCase());
        school.setFacebookURL(school.getFacebookURL().toLowerCase());
        school.setTwitterURL(school.getTwitterURL().toLowerCase());

        String convertedSchoolName = school.getName().toLowerCase();
        school.setName(toTitleCase(convertedSchoolName));
        school.setDateCreated(new Date());

        Optional<SchoolDetail> checkIfSchoolExist = repository.findByName(school.getName());

        if (checkIfSchoolExist.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("School with Name: " + school.getName() + " already exists!!"));
        }
        repository.save(school);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "School Detail Have Been Added!"));
    }

    //GET ALL SCHOOL DETAILS
    public List<SchoolDetail> getSchoolDetail() {
        return repository.findAll();
    }

    public ResponseEntity getSchoolDetailById(Long id) {
        Optional<SchoolDetail> getSchool = repository.findById(id);
        if (getSchool.isPresent()) {
            return ResponseEntity.ok(getSchool.get());
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage));

    }

    public ResponseEntity editSchoolDetailById(Long id, SchoolDetail school) {
        Optional<SchoolDetail> getSchool = repository.findById(id);
        if (getSchool.isPresent()) {
            getSchool.get().setAddress(school.getAddress());
            getSchool.get().setAdminContact(school.getAdminContact());
            getSchool.get().setAltPhone(school.getAltPhone());
            getSchool.get().setCity(school.getCity());
            getSchool.get().setCountry(school.getCountry());
            getSchool.get().setEmail(school.getEmail());
            getSchool.get().setFacebookURL(school.getFacebookURL());
            getSchool.get().setLogoURL(school.getLogoURL());
            getSchool.get().setName(school.getName());
            getSchool.get().setPhone(school.getPhone());
            getSchool.get().setPostalCode(school.getPostalCode());
            getSchool.get().setSchoolCode(school.getSchoolCode());
            getSchool.get().setState(school.getState());
            getSchool.get().setTwitterURL(school.getTwitterURL());
            getSchool.get().setYoutubeURL(school.getYoutubeURL());

            SchoolDetail updatedSchool = repository.save(getSchool.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "School Detail Updated!"));

        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "School with id: " + id + " doesn't Exist"));
    }

    public ResponseEntity deleteSchoolById(Long id) {
        Optional<SchoolDetail> findSchool = repository.findById(id);
        if (findSchool.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.DeletedMessage));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFound, "School with id: " + id + " doesn't Exist"));
    }


}
