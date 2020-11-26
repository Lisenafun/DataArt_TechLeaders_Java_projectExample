package ru.dataart.techleaders.projectExample.services;

import ru.dataart.techleaders.projectExample.dto.MovieDto;

import java.util.List;

public interface MovieService {
    
    List<MovieDto> getMovies();

    Integer addMovie(MovieDto movieDto);
}
