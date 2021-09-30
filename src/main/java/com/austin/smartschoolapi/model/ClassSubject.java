package com.austin.smartschoolapi.model;

import com.austin.smartschoolapi.model.auth.Role;
import com.austin.smartschoolapi.model.auth.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "class_subject")
public class ClassSubject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @ManyToOne
    private Class classId;


    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne
    private Subject subjectId;
}
