package com.austin.smartschoolapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "health_record")
@Getter
@Setter
public class HealthRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complexity")
    private String complexity;

    @Column(name = "weight")
    private String weight;

    @Column(name = "height")
    private String height;

    @Column(name = "genotype")
    private String genotype;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "allergic_history")
    private String allergicHistory;

    @Column(name = "clinical_history")
    private String clinicalHistory;

    @Column(name = "emergency_numbers")
    private String emergencyNumber;

    @Column(name = "date_created")
    private Date dateCreated;

    public HealthRecord() {
    }
}
