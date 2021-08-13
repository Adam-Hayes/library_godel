package com.godel.library.service;

import com.godel.library.controller.dto.MovieCriteria;
import com.godel.library.model.Movie;
import com.godel.library.repository.MovieRepository;
import com.godel.library.repository.MovieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAll(MovieCriteria movieCriteria) {
        Specification<Movie> specification = new MovieSpecification()
                .dates(movieCriteria.getStartDate(), movieCriteria.getEndDate())
                .directorNames(movieCriteria.getDirectorName())
                .build();


        return movieRepository.findAll(specification);
    }


}
