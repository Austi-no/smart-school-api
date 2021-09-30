package com.austin.smartschoolapi.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oauth_client_details")
@Getter
@Setter
public class OauthClientDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;


    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;

    @Column(name = "scope")
    private String scope;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;


    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "resource_ids")
    private String resourceIds;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "additional_information", length = 4096)
    private String additionalInformation;

    @Column(name = "autoapprove")
    private String autoapprove;


    @Column(name = "authorities")
    private String authorities;


    public OauthClientDetails() {
    }


}