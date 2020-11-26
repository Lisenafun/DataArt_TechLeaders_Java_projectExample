package ru.dataart.techleaders.projectExample.dao;

import ru.dataart.techleaders.projectExample.dto.MovieDto;

import java.util.List;

public interface MovieDao {

    List<MovieDto> getMovies();

    void createTable();

    Integer addMovie(MovieDto movieDto);
}
