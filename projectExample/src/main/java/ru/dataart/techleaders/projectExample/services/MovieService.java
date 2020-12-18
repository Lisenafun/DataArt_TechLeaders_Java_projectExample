package ru.dataart.techleaders.projectExample.services;

import ru.dataart.techleaders.projectExample.dto.MovieDtoForUI;

import java.util.List;

public interface MovieService {

    void addMovie(MovieDtoForUI movieForUI);

    MovieDtoForUI findById(Integer id);

    List<MovieDtoForUI> findByName(String name);

    List<MovieDtoForUI> findByDirector(String director);

    List<MovieDtoForUI> findByGenre(String genre);

    List<MovieDtoForUI> getMovies();
}
