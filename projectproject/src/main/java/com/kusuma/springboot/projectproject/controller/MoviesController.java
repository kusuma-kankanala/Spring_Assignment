package com.kusuma.springboot.projectproject.controller;


import com.kusuma.springboot.projectproject.entity.Movies;
import com.kusuma.springboot.projectproject.entity.Users;
import com.kusuma.springboot.projectproject.repository.MyUserDetails;
import com.kusuma.springboot.projectproject.services.MoviesService;
import com.kusuma.springboot.projectproject.services.UsersServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/movies")
public class MoviesController {
    private MoviesService moviesService;
    private UsersServiceInterface usersService;

    // logger added
    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    private static int movieIdToCinephile;

    @Autowired
    public MoviesController(MoviesService moviesService,
                            UsersServiceInterface usersService){

        this.moviesService = moviesService;
        this.usersService = usersService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor trimSpaces = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, trimSpaces);
    }

    @GetMapping("/list")
    public String listEvents(Model model){
        List<Movies> movies = moviesService.listMovies();

        model.addAttribute("movie", movies);

        return "list-movies";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Movies movie = new Movies();

        model.addAttribute("movie", movie);

        return "movie-form";
    }

    @PostMapping("/save")
    public String saveEvent(@Valid @ModelAttribute("movie") Movies movie,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "movie-form";
        }

        moviesService.saveMovie(movie);

        return "redirect:/api/movies/list";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("movieId") int theId,
                                    Model theModel) {

        Movies theMovie = moviesService.getMovieById(theId);

        theModel.addAttribute("movie", theMovie);

        return "movie-form";
    }

    @PostMapping("/register")
    public String showFormRegister(@RequestParam("movieId") int theId,
                                   Model theModel, RedirectAttributes redirAttrs) {

        Movies theMovie = moviesService.getMovieById(theId);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof MyUserDetails){
            String username = ((MyUserDetails)principal).getUsername();
            List<Users> usersList = theMovie.getUsersList();

            boolean flag = false;

            for(Users user: usersList){
                if(user.getUsername().equals(username)){
                    flag = true;
                    break;
                }
            }

            if(!flag){
                // add user as it has not enrolled in the course
                Users users = usersService.getUserByUsername(username);
                usersList.add(users);
                theMovie.setUsersList(usersList);
                moviesService.saveMovie(theMovie);
            }
            else{
                logger.warn(">>>>>> movie already registered in the database!!");

                redirAttrs.addFlashAttribute("error", username + " already booked Ticket ");
                return "redirect:/api/movies/list";
            }
        }

        logger.debug(">>>>>>> movie registered in the database");

        theModel.addAttribute("movie", theMovie);

        redirAttrs.addFlashAttribute("success", "Ticket Booked");

        return "redirect:/api/movies/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("movieId") int theId) {
        moviesService.deleteMovie(theId);

        return "redirect:/api/movies/list";

    }

    @PostMapping("/movieDetails")
    public String movieDetails(@RequestParam("movieId") int theId,
                               Model theModel){

        Movies theMovie = moviesService.getMovieById(theId);

        movieIdToCinephile = theId;

        theModel.addAttribute("movie", theMovie);
        return "movie-details";
    }

    public static int passMovieIdToCinephile(){
        return movieIdToCinephile;
    }
}
