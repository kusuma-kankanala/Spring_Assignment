package com.kusuma.springboot.projectproject.services;

import com.kusuma.springboot.projectproject.entity.Cinephiles;

import java.util.List;

public interface CinephileService {
    List<Cinephiles> listCinephiles();
    Cinephiles getCinephileById(int id);
    Cinephiles saveCinephile(Cinephiles speaker);
    void deleteCinephile(int id);
}
