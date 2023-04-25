package com.szalai.neo4jexample;

import com.szalai.neo4jexample.model.Movie;
import com.szalai.neo4jexample.model.Person;
import com.szalai.neo4jexample.repository.MovieRepository;
import com.szalai.neo4jexample.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Neo4jExampleApplication {

    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Neo4jExampleApplication.class, args);
    }

    @PostConstruct
    public void seed(){
        if (personRepository.findAll().isEmpty()){
            Set<Person> people = createPeople();
            Set<Movie> movies = createMovies();

            Map<String, Set<Person>> actorsMap = selectActors(people);
            Map<String, Set<Person>> directorsMap = selectDirectors(people);

            actorsMap.forEach((aKey, aValue) -> {
                movies.stream()
                        .filter(movie -> movie.getTitle().equals(aKey))
                        .findFirst().orElseThrow().setActors(aValue);
            });
            directorsMap.forEach((dKey, dValue) -> {
                movies.stream()
                        .filter(movie -> movie.getTitle().equals(dKey))
                        .findFirst().orElseThrow().setDirectors(dValue);
            });

            personRepository.saveAll(people);
            movieRepository.saveAll(movies);
            log.info("Successfully inserted new records...");
        }
        else{
            log.info("Database already seeded, continuing without inserting new records...");
        }

    }

    private Map<String, Set<Person>> selectDirectors(Set<Person> people) {
        Map<String, Set<Person>> directorsMap = new HashMap<>();
        directorsMap.put("Movie 1", people.stream().limit(2).collect(Collectors.toSet()));
        directorsMap.put("Movie 2", people.stream().skip(5).limit(1).collect(Collectors.toSet()));
        directorsMap.put("Movie 3", people.stream().skip(10).limit(3).collect(Collectors.toSet()));
        directorsMap.put("Movie 4", people.stream().skip(15).limit(1).collect(Collectors.toSet()));
        directorsMap.put("Movie 5", people.stream().skip(22).limit(2).collect(Collectors.toSet()));
        return directorsMap;
    }

    private Map<String, Set<Person>> selectActors(Set<Person> people) {
        Map<String, Set<Person>> actorsMap = new HashMap<>();
        actorsMap.put("Movie 1", people.stream().limit(5).collect(Collectors.toSet()));
        actorsMap.put("Movie 2", people.stream().skip(5).limit(6).collect(Collectors.toSet()));
        actorsMap.put("Movie 3", people.stream().skip(10).limit(5).collect(Collectors.toSet()));
        actorsMap.put("Movie 4", people.stream().skip(15).limit(7).collect(Collectors.toSet()));
        actorsMap.put("Movie 5", people.stream().skip(20).limit(2).collect(Collectors.toSet()));
        return actorsMap;
    }

    private Set<Person> createPeople() {
        Set<Person> people = new HashSet<>();
        for (int i = 1; i < 25; i++) {
            Person p = new Person();
            p.setName("Person " + i);
            p.setBorn(new Random().nextInt(1968, 1998));
            people.add(p);
        }
        return people;
    }
    private Set<Movie> createMovies() {
        Set<Movie> movies = new HashSet<>();
        for (int i = 1; i < 6; i++) {
            Movie m = new Movie();
            m.setTitle("Movie " + i);
            m.setDescription("Description of Movie " + i);
            movies.add(m);
        }
        return movies;
    }
}
