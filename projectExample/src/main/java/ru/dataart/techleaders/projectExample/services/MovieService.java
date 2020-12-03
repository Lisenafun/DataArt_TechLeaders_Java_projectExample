package ru.dataart.techleaders.projectExample.services;

import ru.dataart.techleaders.projectExample.dto.MovieDto;

import java.util.List;

public interface MovieService {
    
    List<MovieDto> getMovies();
    void addMovie(MovieDto movieDto);
    List<MovieDto> findByName(String name);
    List<MovieDto> findByDirector(String director);
    List<MovieDto> findByGenre(String genre);
}
