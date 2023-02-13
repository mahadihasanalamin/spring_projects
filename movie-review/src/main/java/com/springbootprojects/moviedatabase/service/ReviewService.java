package com.springbootprojects.moviedatabase.service;

import com.springbootprojects.moviedatabase.model.Movie;
import com.springbootprojects.moviedatabase.model.Review;
import com.springbootprojects.moviedatabase.repository.MovieRepository;
import com.springbootprojects.moviedatabase.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String imdbId, Review review){
        Review rev = reviewRepository.insert(review);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(rev))
                .first();
        return rev;
    }
}
