package com.austin.smartschoolapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "employee_qualification")
@Getter
@Setter
public class EmployeeQualification implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level_of_qualification")
    private String levelOfQualification ;

    @Column(name = "institution")
    private String institution ;

    @Column(name = "date_of_issuance")
    private Date dateOfIssuance;

    @Column(name = "date_created")
    private Date dateCreated ;

    public EmployeeQualification() {
    }
}
