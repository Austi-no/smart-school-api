package com.austin.smartschoolapi.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "role_user")
public class RoleUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;


    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private Role roleId;


    public RoleUser() {
    }


}
