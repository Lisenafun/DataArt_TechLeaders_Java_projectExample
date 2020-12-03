package ru.dataart.techleaders.projectExample.dao;

import ru.dataart.techleaders.projectExample.dto.GenreDto;

import java.util.List;

public interface GenreDao {

    void addGenre(GenreDto genreDto);
    Integer findByName(String name);
    String findByGenreId(Integer genreId);
    List<Integer> findByMovieId(Integer movieId);
}
