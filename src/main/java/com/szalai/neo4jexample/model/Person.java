package com.szalai.neo4jexample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@NoArgsConstructor
@Node(value = "Person")
public class Person {

    @Id
    private String name;
    private Integer born;

    public Person(String name, Integer born) {
        this.name = name;
        this.born = born;
    }
}
