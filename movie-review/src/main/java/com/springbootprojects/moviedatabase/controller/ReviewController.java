package com.springbootprojects.moviedatabase.controller;

import com.springbootprojects.moviedatabase.model.Review;
import com.springbootprojects.moviedatabase.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/movies/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/{imdbId}")
    public ResponseEntity<Review> createReview(@PathVariable String imdbId, @RequestBody Review review){
        return new ResponseEntity<Review>(reviewService.createReview(imdbId, review), HttpStatus.CREATED);
    }
}
