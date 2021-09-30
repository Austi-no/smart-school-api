package com.austin.smartschoolapi.service;

import com.austin.smartschoolapi.model.Contact;
import com.austin.smartschoolapi.model.Guardian;
import com.austin.smartschoolapi.repository.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class AppService {
    @Autowired
    private AcademicSessionRepository academicSessionRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeQualificationRepository employeeQualificationRepository;

    @Autowired
    private EmployeeRankRepository employeeRankRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Autowired
    private SchoolDetailRepository schoolDetailRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentHouseRepository studentHouseRepository;

    @Autowired
    private SubjectRepository subjectRepository;



}
