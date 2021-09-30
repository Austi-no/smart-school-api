package com.austin.smartschoolapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "subject")
@Data
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject ;

    @Column(name = "subject_type")
    private String subjectType ;

    @Column(name = "date_created")
    private Date dateCreated ;

    @JoinColumn(name = "subject_teacher", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Employee subjectTeacher;

    @JoinColumn(name = "section", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Section section;

}
