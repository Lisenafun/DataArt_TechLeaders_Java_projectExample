package ru.dataart.techleaders.projectExample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dataart.techleaders.projectExample.dao.MovieDao;
import ru.dataart.techleaders.projectExample.dto.MovieDto;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDao movieDao;

    @Override
    public List<MovieDto> getMovies() {
        return movieDao.findAll();
    }

    @Override
    public void addMovie(MovieDto movieDto) {
        movieDao.addMovie(movieDto);
    }

    @Override
    public List<MovieDto> findByName(String name) {
        return movieDao.findByName(name);
    }

    @Override
    public List<MovieDto> findByDirector(String director) {
        return movieDao.findByDirector(director);
    }

    @Override
    public List<MovieDto> findByGenre(String genre) {
        return movieDao.findByGenre(genre);
    }
}
