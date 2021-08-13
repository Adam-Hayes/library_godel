package com.godel.library.repository;

import com.godel.library.model.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Objects;

public class MovieSpecification {
    private Specification<Movie> specification = Specification.where(null);

    public MovieSpecification dates(LocalDate startDate, LocalDate endDate) {
        Specification<Movie> scope;

        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            scope = (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("releaseDate"), startDate, endDate);
        } else if (Objects.nonNull(startDate)) {
            scope = (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("releaseDate"), startDate);
        } else if (Objects.nonNull(endDate)){
            scope = (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("releaseDate"), endDate);
        } else {
            return this;
        }

        specification = specification.and(scope);
        return this;
    }

    public MovieSpecification directorNames(String directorName) {
        if (directorName == null) {
            return this;
        }
        Specification<Movie> scope = (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.join("director").get("lastName")), "%" + directorName.toLowerCase() + "%");
        specification = specification.and(scope);
        return this;
    }

    public Specification<Movie> build() {
        return specification;
    }
}
