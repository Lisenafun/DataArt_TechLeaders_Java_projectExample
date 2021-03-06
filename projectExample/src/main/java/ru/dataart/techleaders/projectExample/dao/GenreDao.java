package ru.dataart.techleaders.projectExample.dao;

import ru.dataart.techleaders.projectExample.dto.GenreDto;

import java.util.List;

public interface GenreDao {

    void addGenre(GenreDto genreDto);

    Integer findGenreByName(String name);

    String findGenreById(Integer genreId);

    List<Integer> findGenreByMovieId(Integer movieId);

    void addGenreOfMovie(Integer movieId, Integer genreId);
}
