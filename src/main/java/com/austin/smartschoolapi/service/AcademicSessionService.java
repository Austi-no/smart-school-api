package com.austin.smartschoolapi.service;

import com.austin.smartschoolapi.constants.ApiResponse;
import com.austin.smartschoolapi.constants.CustomMessages;
import com.austin.smartschoolapi.model.AcademicSession;
import com.austin.smartschoolapi.repository.AcademicSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AcademicSessionService {

    @Autowired
    private AcademicSessionRepository academicSessionRepository;


    public ResponseEntity addSession(AcademicSession session) {
        Optional<AcademicSession> optionalAcademicSession = academicSessionRepository.findBySession(session.getSession());
        if (optionalAcademicSession.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, session.getSession() + " Already Exist!"));
        }
        session.setDateCreated(new Date());
        session.setIsActive(false);
        academicSessionRepository.save(session);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, session.getSession() + " Session Created Successfully!"));
    }

    public List<AcademicSession> getSessions() {
        return academicSessionRepository.findAll();
    }

    public ResponseEntity getSessionById(Long id) {
        Optional<AcademicSession> optionalSession = academicSessionRepository.findById(id);

        if (optionalSession.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalSession.get()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Session was not Found!"));
    }

    public ResponseEntity updateSession(Long id, AcademicSession session) {
        Optional<AcademicSession> optionalSession = academicSessionRepository.findById(id);

        if (optionalSession.isPresent()) {
            optionalSession.get().setSession(session.getSession());
            optionalSession.get().setLastUpdated(new Date());
            academicSessionRepository.save(optionalSession.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "Session Updated!"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Session was not Found!"));
    }

    public ResponseEntity deleteSession(Long id) {
        Optional<AcademicSession> optionalSession = academicSessionRepository.findById(id);
        if (optionalSession.isPresent()) {
            academicSessionRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, optionalSession.get().getSession() + "Session has been Deleted!"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Session was not Found!"));
    }

    public ResponseEntity getCurrentSession() {
        List<AcademicSession> academicSessionList = academicSessionRepository.findAll();

        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, academicSessionList.stream().filter(x -> x.getIsActive() == true)));
    }


    public ResponseEntity changeStatus(Long sessionId, Boolean isActive) {
        Optional<AcademicSession> optionalSession = academicSessionRepository.findById(sessionId);
        if (optionalSession.isPresent()) {
            optionalSession.get().setIsActive(isActive);
            AcademicSession updatedSession = academicSessionRepository.save(optionalSession.get());
            if (updatedSession.getIsActive() == true) {
                return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalSession.get().getSession() + " status has been enabled!"));
            }
            if (updatedSession.getIsActive() == false) {
                return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalSession.get().getSession() + " status has been Disabled!"));
            }

        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Session was not Found!"));
    }
}
