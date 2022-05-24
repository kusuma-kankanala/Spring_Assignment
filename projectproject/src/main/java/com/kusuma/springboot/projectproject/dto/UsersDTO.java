package com.kusuma.springboot.projectproject.dto;

import com.kusuma.springboot.projectproject.entity.Movies;
import com.kusuma.springboot.projectproject.entity.Roles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UsersDTO {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String branch;
    private List<Movies> moviesList;
    private Set<Roles> roles;
}
