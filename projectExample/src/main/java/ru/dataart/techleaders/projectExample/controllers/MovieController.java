package ru.dataart.techleaders.projectExample.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dataart.techleaders.projectExample.dto.MovieDtoForUI;
import ru.dataart.techleaders.projectExample.services.MovieService;

import java.util.List;

@Controller
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDtoForUI>> getAll(@RequestParam(name = "name", required = false) String name,
                                                      @RequestParam(name = "director", required = false) String director,
                                                      @RequestParam(name = "genre", required = false) String genre) {
        if(name != null) {
            List<MovieDtoForUI> movies = movieService.findByName(name);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        if(director != null) {
            List<MovieDtoForUI> movies = movieService.findByDirector(director);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        if(genre != null) {
            List<MovieDtoForUI> movies = movieService.findByGenre(genre);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        List<MovieDtoForUI> movies = movieService.getMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDtoForUI> getById(@PathVariable Integer id) {
        MovieDtoForUI movie = movieService.findById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody MovieDtoForUI dtoForUI) {
        movieService.addMovie(dtoForUI);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
