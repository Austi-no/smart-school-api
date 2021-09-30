package com.austin.smartschoolapi.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "section")
@Data
public class Section  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "section")
    private String section;

    @Column(name ="start_date")
    private  Date startDate;

    @Column(name ="end_date")
    private  Date endDate;

    @Column(name ="date_created")
    private  Date dateCreated;

    @JoinColumn(name = "academic_session", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private AcademicSession academicSession;





}
