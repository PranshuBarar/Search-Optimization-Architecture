package com.optimisesearch.kafka_consumer_for_search_optimization.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private int user_id;
    private String email;
    private String username;
    private String password;
}
