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
        return movieDao.getMovies();
    }

    @Override
    public Integer addMovie(MovieDto movieDto) {
        return movieDao.addMovie(movieDto);
    }
}
