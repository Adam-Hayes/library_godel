package com.godel.library.controller.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MovieCriteria {

    private String directorName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public MovieCriteria() {
    }

    public MovieCriteria(String directorName, LocalDate startDate, LocalDate endDate) {
        this.directorName = directorName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
