package com.optimisesearch.kafka_consumer_for_search_optimization.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SharingTransactionDTO {
    private int sharing_transaction_id;
    private int note_id;
    private int recipient_id;
}
