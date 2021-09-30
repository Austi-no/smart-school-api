package com.austin.smartschoolapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee_rank")
@Getter
@Setter
public class EmployeeRank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rank")
    private String rank ;

    @Column(name = "date_started")
    private Date dateStarted ;

    @Column(name = "date_created")
    private Date dateCreated ;

    public EmployeeRank() {
    }
}
