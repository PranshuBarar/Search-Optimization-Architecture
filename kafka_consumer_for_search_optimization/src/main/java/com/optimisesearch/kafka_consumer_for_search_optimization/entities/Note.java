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
@Document(indexName = "note_index", createIndex = true)
public class Note {

    @Id
    @Field(name = "note_id")
    private int noteId;

    @Field(name = "note")
    private String note;

    //this is owner user id
    @Field(name = "note_owner_id")
    private int noteOwnerId;
}
