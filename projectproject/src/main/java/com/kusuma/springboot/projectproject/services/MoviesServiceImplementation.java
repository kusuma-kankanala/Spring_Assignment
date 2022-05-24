package com.kusuma.springboot.projectproject.services;

import com.kusuma.springboot.projectproject.entity.Movies;
import com.kusuma.springboot.projectproject.repository.MoviesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesServiceImplementation implements MoviesService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public List<Movies> listMovies() {

        return moviesRepository.findAll();
    }

    @Override
    public Movies getMovieById(int id) {
        Optional<Movies> result = Optional.of(moviesRepository.getById(id));

        return result.get();
    }

    @Override
    public Movies saveMovie(Movies movie) {

        moviesRepository.save(movie);

        return movie;
    }

    @Override
    public void deleteMovie(int id) {

        moviesRepository.deleteById(id);
    }
}
