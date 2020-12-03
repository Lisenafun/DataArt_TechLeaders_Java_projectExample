package ru.dataart.techleaders.projectExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dataart.techleaders.projectExample.dto.MovieDto;
import ru.dataart.techleaders.projectExample.services.MovieService;

import java.util.List;

@Controller
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll(@RequestParam(name = "name", required = false) String name,
                                                       @RequestParam(name = "director", required = false) String director,
                                                       @RequestParam(name = "genre", required = false) String genre) {
        if(name !=null){
            List<MovieDto> movies = movieService.findByName(name);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        if(director != null){
            List<MovieDto> movies = movieService.findByDirector(director);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        if(genre != null) {
            List<MovieDto> movies = movieService.findByGenre(genre);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        List<MovieDto> movies = movieService.getMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody MovieDto movieDto){
        movieService.addMovie(movieDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
