package com.szalai.neo4jexample.controller;

import com.szalai.neo4jexample.controller.request.MovieRequest;
import com.szalai.neo4jexample.model.Movie;
import com.szalai.neo4jexample.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Neo4jExampleController {

    private final MovieRepository movieRepository;

    public Neo4jExampleController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{title}")
    public Movie getOneMovie(@PathVariable String title){
        return movieRepository.findOneByTitle(title);
    }

    @PostMapping("/movies")
    public Movie addOneMovie(@RequestBody MovieRequest request){
        return movieRepository.save(new Movie(request.title(), request.description()));
    }

    @DeleteMapping("/movies/{title}")
    public void deleteOneMovie(@PathVariable String title){
        Movie movie = movieRepository.findOneByTitle(title);
        movieRepository.delete(movie);
    }
}
