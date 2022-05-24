package com.kusuma.springboot.projectproject.repository;

import com.kusuma.springboot.projectproject.entity.Roles;
import com.kusuma.springboot.projectproject.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetails implements UserDetails {
    private transient Users user;

    public MyUserDetails(Users user) {

        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Roles> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    // adding some methods to get user details
    public int getUserId(){
        return user.getId();
    }

    public String getFirstName(){
        return user.getFirstName();
    }

    public String getLastName(){
        return user.getLastName();
    }

    public int getAge(){
        return user.getAge();
    }

    public String getGender(){
        return user.getGender();
    }

    public String getBranch(){
        return user.getBranch();
    }
}
