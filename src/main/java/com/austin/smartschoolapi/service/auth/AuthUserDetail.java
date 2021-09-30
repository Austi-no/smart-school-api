package com.austin.smartschoolapi.service.auth;


import com.austin.smartschoolapi.model.auth.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AuthUserDetail extends User implements UserDetails {

    public AuthUserDetail(User user) {
        super(user);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//            role.getPermissions().forEach(permission -> {
//                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
//            });

        });
        return grantedAuthorities;
    }


}