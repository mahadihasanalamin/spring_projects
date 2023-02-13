package com.springbootprojects.moviedatabase.repository;

import com.springbootprojects.moviedatabase.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
//    @Query("select m from movies m where m.imdbId= :imdbId")
    Movie findMovieByImdbId(String imdbId);
}
