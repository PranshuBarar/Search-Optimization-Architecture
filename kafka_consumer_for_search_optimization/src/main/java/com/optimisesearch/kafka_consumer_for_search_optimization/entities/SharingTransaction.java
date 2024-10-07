package com.optimisesearch.kafka_consumer_for_search_optimization.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "shared_index", createIndex = true)
public class SharingTransaction {
    @Id
    @Field(name = "sharing_transaction_id")
    private int sharingTransactionId;

    @Field(name = "note_id")
    private int parentNoteId;

    @Field(name = "recipient_id")
    private int recipientUserId;
}
