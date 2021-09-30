package com.austin.smartschoolapi.model.auth;


import com.austin.smartschoolapi.model.Employee;
import com.austin.smartschoolapi.model.Guardian;
import com.austin.smartschoolapi.model.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "First Name must not be NULL")
    @NotBlank(message = "First Name must not be BLANK")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name must not be NULL")
    @NotBlank(message = "Last Name must not be BLANK")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Please Provide a Valid Email Address")
    @Column(name = "email")
    private String email;

    //    @Max(value = 11, message = "Phone Number must not be More than 11")
//    @Min(value = 11, message = "Phone Number must not be Less than 11")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Phone Number must be 11 digits")
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "Address must not be NULL")
    @NotBlank(message = "Address must not be BLANK")
    @Column(name = "address")
    private String address;

    @NotNull(message = "Gender must not be NULL")
    @NotBlank(message = "Gender must not be BLANK")
    @Column(name = "gender")
    private String gender;

    @Column(name = "user_type")
    private String userType;

    //    @NotNull(message = "Date of Birth must not be NULL")
//    @NotBlank(message = "Date of Birth must not be BLANK")
    @Column(name = "dob")
    private Date dob;

    @Column(name = "age")
    private String age;
    @NotNull(message = "Username must not be NULL")
    @NotBlank(message = "Username must not be BLANK")
//    @Min(value = 3, message = "Username Must not be less than 6 Characters")
    @Column(name = "username", unique = true)
    private String username;

    @NotNull(message = "Last Name must not be NULL")
    @NotBlank(message = "Last Name must not be BLANK")

//    @Min(value = 3, message = "Password Must not be less than 6 Characters")
    @Column(name = "password")
    private String password;

    @Column(name = "plain_password")
    private String plainPassword;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    //    EMPLOYEE ADDITIONAL DETAILS
    @JoinColumn(name = "employee", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.REFRESH})
    private Employee employee;


    //    STUDENT ADDITIONAL DETAILS
    @JoinColumn(name = "student", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.REFRESH})
    private Student student;


    //    GUARDIAN ADDITIONAL DETAILS
    @JoinColumn(name = "guardian", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.REFRESH})
    private Guardian guardian;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns =
            {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();


    public User() {
    }


    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.plainPassword = user.getPlainPassword();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.accountNonExpired = user.isAccountNonExpired();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.roles = user.getRoles();
        this.dateCreated = user.getDateCreated();
        this.dob = user.getDob();
        this.employee = user.getEmployee();
        this.guardian = user.getGuardian();
        this.age = user.getAge();
        this.student = user.getStudent();
        this.userType = user.getUserType();

    }


}