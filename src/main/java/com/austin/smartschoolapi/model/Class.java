package com.austin.smartschoolapi.model;

import com.austin.smartschoolapi.model.auth.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "class")
@Getter
@Setter
public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "date_created")
    private Date dateCreated ;

    @JoinColumn(name = "academic_session", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private AcademicSession academicSession;

    @JoinColumn(name = "class_teacher", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Employee formTeacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "class_subject", joinColumns =
            {@JoinColumn(name = "class_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "subject_id", referencedColumnName = "id")})
    private Set<Subject> subjectList = new HashSet<>();


    public Class() {
    }
}
