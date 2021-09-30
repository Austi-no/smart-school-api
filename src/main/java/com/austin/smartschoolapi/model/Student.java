package com.austin.smartschoolapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "age")
    private String age;

    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "gender")
    private String gender;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "year_of_admission")
    private Date yearOfAdmission;

    @JoinColumn(name = "academic_session", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private AcademicSession academicSession;

    @JoinColumn(name = "student_house", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private StudentHouse studentHouse;

    @JoinColumn(name = "contact", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Contact contact ;

    @JoinColumn(name = "guardian", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Guardian guardian ;

    @JoinColumn(name = "health_record", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private HealthRecord healthRecord ;



}
