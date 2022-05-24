package com.kusuma.springboot.projectproject.services;

import com.kusuma.springboot.projectproject.entity.Movies;

import java.util.List;

public interface MoviesService {
    List<Movies> listMovies();
    Movies getMovieById(int id);
    Movies saveMovie(Movies event);
    void deleteMovie(int id);
}
