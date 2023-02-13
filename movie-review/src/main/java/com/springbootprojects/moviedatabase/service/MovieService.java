package com.springbootprojects.moviedatabase.service;

import com.springbootprojects.moviedatabase.model.Movie;
import com.springbootprojects.moviedatabase.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> fetchAllMovies(){
        return movieRepository.findAll();
    }

    public Movie fetchMovieById(ObjectId id) {
        return movieRepository.findById(id).get();
    }

    public Movie fetchMovieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
