package com.kusuma.springboot.projectproject;


import com.kusuma.springboot.projectproject.entity.Movies;
import com.kusuma.springboot.projectproject.entity.Roles;
import com.kusuma.springboot.projectproject.entity.Users;
import com.kusuma.springboot.projectproject.repository.RoleRepository;
import com.kusuma.springboot.projectproject.repository.UserRepository;
import com.kusuma.springboot.projectproject.services.UsersServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplementationTest {
    @Autowired
    private UsersServiceInterface usersService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    void listUsers() {
        when(userRepository.findAll()).thenReturn(Stream
                .of(new Users("jack","test123","micheal","jackson",22,"M","IT")).collect(Collectors.toList()));

        assertEquals(1, usersService.listUsers().size());
    }

    @Test
    void getUserById(){
        Users users = new Users("jack","test123","Micheal","Jackson", 22, "M", "IT");
        users.addRole(roleRepository.getById(2));
        users.addMovie(new Movies("python language", "XYZ"));

        when(userRepository.getById(1)).thenReturn(users);

        Users user = usersService.getUserById(1);
        assertEquals("jack", user.getUsername());
        assertEquals("Micheal", user.getFirstName());
        assertEquals("Jackson", user.getLastName());
        assertEquals(22, user.getAge());
        assertEquals("M", user.getGender());
        assertEquals("IT", user.getBranch());
        assertEquals(1, user.getMoviesList().size());
    }

    @Test
    void getUserByUsername(){
        Users users = new Users("jack","test123","Micheal","Jackson", 22, "M", "IT");
        users.addRole(roleRepository.getById(2));
        users.addMovie(new Movies("python language", "XYZ"));
        users.addMovie(new Movies("java language", "ABC"));

        when(userRepository.getUserByUsername("jack")).thenReturn(users);

        Users user = usersService.getUserByUsername("jack");
        assertEquals(users.getId(), user.getId());
        assertEquals("Micheal", user.getFirstName());
        assertEquals("Jackson", user.getLastName());
        assertEquals(22, user.getAge());
        assertEquals("M", user.getGender());
        assertEquals("IT", user.getBranch());
        assertEquals(2, user.getMoviesList().size());
    }

    @Test
    void saveUser() {
        Users users = new Users("jack","test123","micheal","jackson",22,"M","IT");
        Roles role = roleRepository.getById(2);
        users.addRole(role);

        when(userRepository.save(users)).thenReturn(users);

        assertEquals(users, usersService.saveUser(users));
    }

    @Test
    void deleteUser() {
        Users users = new Users("jack","test123","micheal","jackson",22,"M","IT");
        Roles role = roleRepository.getById(2);
        users.addRole(role);

        usersService.deleteUser(users.getId());
        verify(userRepository, times(1)).deleteById(users.getId());
    }
}