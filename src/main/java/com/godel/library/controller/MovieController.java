package com.godel.library.controller;

import com.godel.library.controller.dto.MovieCriteria;
import com.godel.library.model.Movie;
import com.godel.library.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/show")
    public String getAllMovies(MovieCriteria criteria, Model model) {
        List<Movie> movies = movieService.getAll(criteria);
        model.addAttribute("movies", movies);

        return "show";

    }


}
