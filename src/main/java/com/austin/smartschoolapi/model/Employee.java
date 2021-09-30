package com.austin.smartschoolapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "age")
    private String age;


    @Column(name = "employment_date")
    private Date employmentDate;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name = "date_created")
    private Date dateCreated;

    @JoinColumn(name = "employee_qualification", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private EmployeeQualification employeeQualification;

    @JoinColumn(name = "rank", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private EmployeeRank employeeRank;

    @JoinColumn(name = "assigned_class", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Class assignedClass;

    @JoinColumn(name = "assigned_subject", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Subject assignedSubject;

    public Employee() {
    }

}
