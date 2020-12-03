package ru.dataart.techleaders.projectExample.dao;

import ru.dataart.techleaders.projectExample.dto.MovieDto;

import java.util.List;

public interface MovieDao {

    void createTable();
    void addMovie(MovieDto movieDto);
    List<MovieDto> findAll();
    MovieDto findById(Integer id);
    List<MovieDto> findByName(String name);
    List<MovieDto> findByDirector(String director);
    List<MovieDto> findByGenre(String genre);
}
