package com.kusuma.springboot.projectproject.services;

import com.kusuma.springboot.projectproject.entity.Cinephiles;
import com.kusuma.springboot.projectproject.repository.CinephilesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinephileServiceImplementation implements CinephileService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CinephilesRepository cinephilesRepository;


    @Override
    public List<Cinephiles> listCinephiles() {
        return cinephilesRepository.findAll();
    }

    @Override
    public Cinephiles getCinephileById(int id) {
        Optional<Cinephiles> result = Optional.of(cinephilesRepository.getById(id));

        return result.get();
    }

    @Override
    public Cinephiles saveCinephile(Cinephiles cinephile) {

        cinephilesRepository.save(cinephile);
        return cinephile;
    }

    @Override
    public void deleteCinephile(int id) {

        cinephilesRepository.deleteById(id);
    }


}
