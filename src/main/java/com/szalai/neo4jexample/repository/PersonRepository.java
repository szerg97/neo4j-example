package com.szalai.neo4jexample.repository;

import com.szalai.neo4jexample.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, String> {
}
