package com.godel.library.service;

import com.godel.library.controller.dto.MovieCriteria;
import com.godel.library.model.Director;
import com.godel.library.model.Movie;
import com.godel.library.repository.MovieRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    public static final String DIRECTOR_NAME = "Parker";
    public static final String MOVIE_NAME = "KEVIN_ONETWO";
    public static final String GENRE = "HORROR";

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    public static Stream<Arguments> movieCriteriaValueGenerator() {
        return Stream.of(
                Arguments.arguments(
                        DIRECTOR_NAME, null, null, buildResult(buildDirector(DIRECTOR_NAME), null),
                        null, LocalDate.now(), null, buildResult(buildDirector(DIRECTOR_NAME), LocalDate.now().plusDays(1)),
                        DIRECTOR_NAME, LocalDate.now(), LocalDate.now().plusDays(5), buildResult(buildDirector(DIRECTOR_NAME), LocalDate.now().plusDays(1))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("movieCriteriaValueGenerator")
    void getAll_shouldReturnResultByCriteria(String directorName,
                                             LocalDate startDate,
                                             LocalDate endDate,
                                             List<Movie> expectedResult) {
        MovieCriteria movieCriteria = movieCriteriaBuilder(directorName, startDate, endDate);

        Mockito.when(movieRepository.findAll(Mockito.any(Specification.class))).thenReturn(expectedResult);

        List<Movie> actual = movieService.getAll(movieCriteria);

        assertEquals(expectedResult, actual);
        Mockito.verify(movieRepository, Mockito.times(1)).findAll(Mockito.any(Specification.class));
    }


    private MovieCriteria movieCriteriaBuilder(String directorName,
                                               LocalDate startDate,
                                               LocalDate endDate) {
        MovieCriteria movieCriteria = new MovieCriteria();
        movieCriteria.setDirectorName(directorName);
        movieCriteria.setStartDate(startDate);
        movieCriteria.setEndDate(endDate);

        return movieCriteria;
    }

    private static List<Movie> buildResult(Director director, LocalDate releaseDate) {
        return List.of(
                new Movie(MOVIE_NAME, GENRE, releaseDate, director),
                new Movie(MOVIE_NAME, GENRE, releaseDate, director));
    }

    private static Director buildDirector(String directorName) {
        Director director = new Director();
        director.setLastName(directorName);

        return director;
    }


}