package com.szalai.neo4jexample.repository;

import com.szalai.neo4jexample.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MovieRepository extends Neo4jRepository<Movie, String> {
    Movie findOneByTitle(String title);
}
