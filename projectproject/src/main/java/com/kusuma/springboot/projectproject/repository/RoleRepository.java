package com.kusuma.springboot.projectproject.repository;

import com.kusuma.springboot.projectproject.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
}
