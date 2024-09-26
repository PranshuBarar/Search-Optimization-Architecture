package com.optimisesearch.kafka_consumer_for_search_optimization.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "user_index", createIndex = true)
public class User {

    @Id
    @Field(name = "user_id")
    private int userId;

    @Field(name = "email")
    private String email;

    @Field(name = "username")
    private String username;

    @Field(name = "password")
    private String password;

}
