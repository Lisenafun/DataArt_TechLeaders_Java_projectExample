package ru.dataart.techleaders.projectExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dataart.techleaders.projectExample.dto.MovieDto;
import ru.dataart.techleaders.projectExample.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<MovieDto>> getAll() {
        List<MovieDto> movies = movieService.getMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody MovieDto movieDto){
        Integer success = movieService.addMovie(movieDto);
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }
}
