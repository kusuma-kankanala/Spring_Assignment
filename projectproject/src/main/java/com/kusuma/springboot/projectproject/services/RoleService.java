package com.kusuma.springboot.projectproject.services;

import com.kusuma.springboot.projectproject.entity.Roles;

import java.util.List;

public interface RoleService {
    List<Roles> listRoles();
    Roles getRoleById(int id);
    void saveRole(Roles role);
    void deleteRole(int id);
}
