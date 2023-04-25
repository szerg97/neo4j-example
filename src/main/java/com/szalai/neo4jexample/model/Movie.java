package com.szalai.neo4jexample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Data
@NoArgsConstructor
@Node(value = "Movie")
public class Movie {
    @Id
    private String title;
    private String description;
    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private Set<Person> actors = new HashSet<>();
    @Relationship(type = "DIRECTED", direction = INCOMING)
    private Set<Person> directors = new HashSet<>();

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
