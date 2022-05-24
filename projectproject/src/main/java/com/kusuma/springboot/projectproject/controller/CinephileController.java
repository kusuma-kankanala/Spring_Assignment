package com.kusuma.springboot.projectproject.controller;

import com.kusuma.springboot.projectproject.entity.Movies;
import com.kusuma.springboot.projectproject.entity.Cinephiles;
import com.kusuma.springboot.projectproject.services.MoviesService;
import com.kusuma.springboot.projectproject.services.CinephileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/cinephiles")
public class CinephileController {
    private CinephileService cinephileService;
    private MoviesService moviesService;

    @Autowired
    public CinephileController(CinephileService cinephileService, MoviesService moviesService){

        this.cinephileService = cinephileService;
        this.moviesService = moviesService;
    }

    @GetMapping("/list")
    public String listCinephiles(Model model){
        List<Cinephiles> cinephiles = cinephileService.listCinephiles();

        int movieId = MoviesController.passMovieIdToCinephile();

        List<Cinephiles> newCinephilesList = new ArrayList<>();

        if(cinephiles != null){
            for(Cinephiles cinephile: cinephiles){
                List<Movies> movieCinephiles = cinephile.getMoviesList();
                for(Movies movieCinephile: movieCinephiles){
                    if(movieCinephile.getId() == movieId){
                        newCinephilesList.add(cinephile);
                    }
                }
            }
        }


        model.addAttribute("cinephile", newCinephilesList);

        return "list-cinephiles";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Cinephiles cinephile = new Cinephiles();

        model.addAttribute("cinephile", cinephile);

        return "cinephile-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("cinephile") Cinephiles cinephile, Model theModel,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "cinephile-form";
        }

        int movieId = MoviesController.passMovieIdToCinephile();

        List<Movies> moviesList = cinephile.getMoviesList();

        if(moviesList == null){
            moviesList = new ArrayList<>();
            Movies movies = moviesService.getMovieById(movieId);
            moviesList.add(movies);

            cinephile.setMoviesList(moviesList);
            cinephileService.saveCinephile(cinephile);

            theModel.addAttribute("cinephile", cinephile);

            return "redirect:/api/cinephiles/list";
        }

        boolean flag = false;

        for(Movies movie: moviesList){
            if(movie.getId() == movieId){
                flag = true;
                break;
            }
        }

        if(!flag){
            Movies movies = moviesService.getMovieById(movieId);
            moviesList.add(movies);

            cinephile.setMoviesList(moviesList);
            cinephileService.saveCinephile(cinephile);
        }
        else{
            String alreadyRegistered = cinephile.getCinephileName() + " has already registered to this event";

            theModel.addAttribute("cinephile", alreadyRegistered);

            return "already-register-cinephile";
        }

        theModel.addAttribute("cinephile", cinephile);

        return "redirect:/api/cinephiles/list";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("cinephileId") int theId,
                                    Model theModel) {

        Cinephiles theCinephile = cinephileService.getCinephileById(theId);

        theModel.addAttribute("cinephile", theCinephile);

        return "cinephile-form";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("cinephileId") int theId) {
        cinephileService.deleteCinephile(theId);

        return "redirect:/api/cinephiles/list";

    }
}
