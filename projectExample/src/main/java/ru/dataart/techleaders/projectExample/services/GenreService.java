package ru.dataart.techleaders.projectExample.services;

import ru.dataart.techleaders.projectExample.dto.GenreDto;

import java.util.List;

public interface GenreService {

    void addGenre(GenreDto genreDto);

    void addGenreOfMovie(Integer movieId, Integer genreId);

    Integer findGenreByName(String name);

    String findGenreById(Integer genreId);

    List<Integer> findGenreByMovieId(Integer movieId);
}
