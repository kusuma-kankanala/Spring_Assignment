package com.kusuma.springboot.projectproject.services;

import com.kusuma.springboot.projectproject.entity.Users;

import java.util.List;

public interface UsersServiceInterface {
    List<Users> listUsers();
    Users getUserById(int id);
    Users saveUser(Users user);

    void deleteUser(int id);
    Users getUserByUsername(String username);
}
