package ru.dataart.techleaders.projectExample.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dataart.techleaders.projectExample.dao.DirectorDao;
import ru.dataart.techleaders.projectExample.dao.GenreDao;
import ru.dataart.techleaders.projectExample.dao.MovieDao;
import ru.dataart.techleaders.projectExample.dto.MovieDtoForDB;
import ru.dataart.techleaders.projectExample.dto.MovieDtoForUI;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    private final GenreDao genreDao;

    private final DirectorDao directorDao;

    @Override
    public void addMovie(MovieDtoForUI movieForUI) {
        movieDao.addMovie(converterToMovieDto(movieForUI));
        List<String> genres = movieForUI.getGenres();
        List<Integer> genresId = new ArrayList<>();
        genres.forEach(genre -> genresId.add(genreDao.findGenreByName(genre)));
        genresId.forEach(genreId -> genreDao.addGenreOfMovie(movieForUI.getId(), genreId));
    }

    @Override
    public List<MovieDtoForUI> getMovies() {
        return getListMovieDtoForUI(movieDao.findAllMovies());
    }

    @Override
    public MovieDtoForUI findById(Integer id) {
        MovieDtoForDB movieDtoForDB = movieDao.findMovieById(id);
        return converterToMovieDtoForUI(movieDtoForDB);
    }

    @Override
    public List<MovieDtoForUI> findByName(String name) {
        return getListMovieDtoForUI(movieDao.findMovieByName(name));
    }

    @Override
    public List<MovieDtoForUI> findByDirector(String director) {
        Integer directorId = directorDao.findDirectorByName(director);
        return getListMovieDtoForUI(movieDao.findMovieByDirector(directorId));
    }

    @Override
    public List<MovieDtoForUI> findByGenre(String genre) {
        Integer genreId = genreDao.findGenreByName(genre);
        return getListMovieDtoForUI(movieDao.findMovieByGenre(genreId));
    }

    private MovieDtoForUI converterToMovieDtoForUI(MovieDtoForDB movieDto) {
        MovieDtoForUI movieForUI = new MovieDtoForUI();
        movieForUI.setId(movieDto.getId());
        movieForUI.setName(movieDto.getName());
        movieForUI.setCountry(movieDto.getCountry());
        movieForUI.setReleaseDate(movieDto.getReleaseDate());

        String director = directorDao.findDirectorById(movieDto.getDirectorId());
        movieForUI.setDirector(director);

        List<Integer> genresIdList = genreDao.findGenreByMovieId(movieDto.getId());
        List<String> genres = new ArrayList<>();
        genresIdList.forEach(genresId -> genres.add(genreDao.findGenreById(genresId)));
        movieForUI.setGenres(genres);

        return movieForUI;
    }

    private MovieDtoForDB converterToMovieDto(MovieDtoForUI movieForUI) {
        MovieDtoForDB movieDto = new MovieDtoForDB();
        movieDto.setId(movieForUI.getId());
        movieDto.setName(movieForUI.getName());
        movieDto.setCountry(movieForUI.getCountry());
        movieDto.setReleaseDate(movieForUI.getReleaseDate());

        Integer directorId = directorDao.findDirectorByName(movieForUI.getDirector());
        movieDto.setDirectorId(directorId);

        return movieDto;
    }

    private List<MovieDtoForUI> getListMovieDtoForUI(List<MovieDtoForDB> movieDtos) {
        List<MovieDtoForUI> movies = new ArrayList<>();
        movieDtos.forEach(movieDto -> movies.add(converterToMovieDtoForUI(movieDto)));
        return movies;
    }
}
