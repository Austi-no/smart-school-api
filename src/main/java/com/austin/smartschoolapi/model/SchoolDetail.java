package com.austin.smartschoolapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "school_detail")
@Getter
@Setter
public class SchoolDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "School Name cannot be BLANK")
    @NotNull(message = "School Name cannot be NULL")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "School Address cannot be BLANK")
    @NotNull(message = "School Address cannot be NULL")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "School Email cannot be BLANK")
    @NotNull(message = "School Email cannot be NULL")
    @Email(message = "Provide a Valid Email")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "School Phone cannot be BLANK")
    @NotNull(message = "School Phone cannot be NULL")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Phone Number must be 11 digits")
    @Column(name = "phone")
    private String phone;

    @Pattern(regexp = "(^$|[0-9]{11})", message = "Phone Number must be 11 digits")
    @Column(name = "alt_phone")
    private String altPhone;

    @NotBlank(message = "Admin Contact cannot be BLANK")
    @NotNull(message = "Admin Contact cannot be NULL")
    @Column(name = "admin_contact")
    private String adminContact;

    @NotBlank(message = "Country cannot be BLANK")
    @NotNull(message = "Country cannot be NULL")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "State cannot be BLANK")
    @NotNull(message = "State cannot be NULL")
    @Column(name = "state")
    private String state;

    @NotBlank(message = "Postal Code cannot be BLANK")
    @NotNull(message = "Postal Code cannot be NULL")
    @Column(name = "postal_code")
    private String postalCode;

    @NotBlank(message = "City cannot be BLANK")
    @NotNull(message = "City cannot be NULL")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "School Code cannot be BLANK")
    @NotNull(message = "School Code cannot be NULL")
    @Column(name = "school_code")
    private String schoolCode;


    @Column(name = "facebook_url")
    private String facebookURL;

    @Column(name = "youtube_url")
    private String youtubeURL;

    @Column(name = "twitter_url")
    private String twitterURL;

    @Column(name = "logo_url")
    private String logoURL;

    @Column(name = "date_created")
    private Date dateCreated;

    public SchoolDetail() {
    }
}
