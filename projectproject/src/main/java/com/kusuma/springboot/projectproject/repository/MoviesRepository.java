package com.kusuma.springboot.projectproject.repository;

import com.kusuma.springboot.projectproject.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {
}
