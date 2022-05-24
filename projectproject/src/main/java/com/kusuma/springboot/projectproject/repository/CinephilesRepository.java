package com.kusuma.springboot.projectproject.repository;


import com.kusuma.springboot.projectproject.entity.Cinephiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinephilesRepository extends JpaRepository<Cinephiles, Integer> {
}
