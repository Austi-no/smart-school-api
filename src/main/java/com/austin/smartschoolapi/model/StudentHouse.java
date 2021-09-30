package com.austin.smartschoolapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student_house")
@Getter
@Setter
public class StudentHouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_code")
    private String houseCode ;

    @Column(name = "student_house")
    private String studentHouse ;

    @Column(name = "date_created")
    private Date dateCreated ;
}
