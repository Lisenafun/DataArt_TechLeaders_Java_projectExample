package ru.dataart.techleaders.projectExample.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dataart.techleaders.projectExample.dao.GenreDao;
import ru.dataart.techleaders.projectExample.dto.GenreDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Integer findGenreByName(String name) {
        return genreDao.findGenreByName(name);
    }

    @Override
    public void addGenre(GenreDto genreDto) {
        genreDao.addGenre(genreDto);
    }

    @Override
    public void addGenreOfMovie(Integer movieId, Integer genreId) {
        genreDao.addGenreOfMovie(movieId, genreId);
    }

    @Override
    public String findGenreById(Integer genreId) {
        return genreDao.findGenreById(genreId);
    }

    @Override
    public List<Integer> findGenreByMovieId(Integer movieId) {
        return genreDao.findGenreByMovieId(movieId);
    }
}
