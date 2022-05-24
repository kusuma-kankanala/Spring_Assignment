package com.kusuma.springboot.projectproject;


import com.kusuma.springboot.projectproject.entity.Cinephiles;
import com.kusuma.springboot.projectproject.entity.Movies;
import com.kusuma.springboot.projectproject.repository.MoviesRepository;
import com.kusuma.springboot.projectproject.services.MoviesService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
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
class MoviesServiceImplementationTest {
    @Autowired
    private MoviesService moviesService;

    @MockBean
    private MoviesRepository moviesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void listEvents() {
        when(moviesRepository.findAll()).thenReturn(Stream
                .of(new Movies("Salaar", "450")).collect(Collectors.toList()));

        assertEquals(1, moviesService.listMovies().size());
        ;
    }

    @Test
    void getEventById(){
        Movies movies = new Movies("Bahubali", "200");
        movies.addSpeaker(new Cinephiles("kusuma","D20"));
        when(moviesRepository.getById(1)).thenReturn(movies);

        Movies event = moviesService.getMovieById(1);
        assertEquals("Bahubali", event.getMovieName());
        assertEquals("200", event.getTotalTickets());

    }

    @Test
    void saveEvent() {
        Movies event = new Movies("Salaar", "200");
        event.addSpeaker(new Cinephiles("saikiran", "B20"));

        when(moviesRepository.save(event)).thenReturn(event);
        assertEquals(event, moviesService.saveMovie(event));
    }

    @Test
    void deleteEvent() {
        Movies event = new Movies("Salaar", "200");
        moviesService.deleteMovie(event.getId());
        verify(moviesRepository, times(1)).deleteById(event.getId());
    }
}