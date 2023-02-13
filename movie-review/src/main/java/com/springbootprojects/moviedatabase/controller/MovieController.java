package com.springbootprojects.moviedatabase.controller;

import com.springbootprojects.moviedatabase.model.Movie;
import com.springbootprojects.moviedatabase.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/")
    public ResponseEntity<List<Movie>> fetchAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.fetchAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> fetchMovieById(@PathVariable ObjectId id){
        return new ResponseEntity<Movie>(movieService.fetchMovieById(id),HttpStatus.OK);
    }

    @GetMapping("/imdb/{imdbId}")
    public ResponseEntity<Movie> fetchMovieByImdbId(@PathVariable String imdbId){
        return new ResponseEntity<Movie>(movieService.fetchMovieByImdbId(imdbId), HttpStatus.OK);
    }
}
