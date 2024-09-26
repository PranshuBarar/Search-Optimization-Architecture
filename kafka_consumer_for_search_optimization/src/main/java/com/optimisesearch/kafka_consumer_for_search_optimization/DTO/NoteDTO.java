package com.optimisesearch.kafka_consumer_for_search_optimization.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDTO {
    private int note_id;
    private String note;
    private int note_owner_id;
}
