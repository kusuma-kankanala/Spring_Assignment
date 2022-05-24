package com.kusuma.springboot.projectproject;



import com.kusuma.springboot.projectproject.entity.Cinephiles;
import com.kusuma.springboot.projectproject.repository.CinephilesRepository;
import com.kusuma.springboot.projectproject.services.CinephileService;
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
class CinephileServiceImplementationTest {
    @Autowired
    private CinephileService cinephileService;

    @MockBean
    private CinephilesRepository cinephilesRepository;

    @Test
    void listSpeakers() {
        when(cinephilesRepository.findAll()).thenReturn(Stream
                .of(new Cinephiles("kusuma", "D20")).collect(Collectors.toList()));

        assertEquals(1, cinephileService.listCinephiles().size());
    }

    @Test
    void getSpeakerById(){
        Cinephiles cinephiles = new Cinephiles("XYZ", "Hacker");
        when(cinephilesRepository.getById(1)).thenReturn(cinephiles);

        Cinephiles speaker = cinephileService.getCinephileById(1);
        assertEquals("XYZ", speaker.getCinephileName());
        assertEquals("Hacker", speaker.getSeatNumber());
    }

    @Test
    void saveSpeaker() {
        Cinephiles cinephiles = new Cinephiles("XYZ", "Metaverse expert");
        when(cinephilesRepository.save(cinephiles)).thenReturn(cinephiles);

        assertEquals(cinephiles, cinephileService.saveCinephile(cinephiles));
    }

    @Test
    void deleteSpeaker() {
        Cinephiles cinephiles = new Cinephiles("XYZ", "Metaverse expert");

        cinephileService.deleteCinephile(cinephiles.getId());
        verify(cinephilesRepository, times(1)).deleteById(cinephiles.getId());
    }
}