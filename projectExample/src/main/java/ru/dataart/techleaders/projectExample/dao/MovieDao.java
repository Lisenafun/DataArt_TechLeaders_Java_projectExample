package ru.dataart.techleaders.projectExample.dao;

import ru.dataart.techleaders.projectExample.dto.MovieDtoForDB;

import java.util.List;

public interface MovieDao {

    void createTable();

    void addMovie(MovieDtoForDB movieDto);

    List<MovieDtoForDB> findAllMovies();

    MovieDtoForDB findMovieById(Integer id);

    List<MovieDtoForDB> findMovieByName(String name);

    List<MovieDtoForDB> findMovieByDirector(Integer director);

    List<MovieDtoForDB> findMovieByGenre(Integer genre);
}
