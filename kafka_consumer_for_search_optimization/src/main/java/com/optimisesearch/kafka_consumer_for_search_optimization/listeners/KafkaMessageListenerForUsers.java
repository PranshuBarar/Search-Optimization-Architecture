package com.optimisesearch.kafka_consumer_for_search_optimization.listeners;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimisesearch.kafka_consumer_for_search_optimization.DTO.UserDTO;
import com.optimisesearch.kafka_consumer_for_search_optimization.entities.User;
import com.optimisesearch.kafka_consumer_for_search_optimization.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaMessageListenerForUsers {

    private final UserService userService;

    @KafkaListener(topics = "dbserver1.search_optimization_db.users")
    public void processMessageFromUsers(String message) {
        System.out.println("This is received message from users: " + message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(message);

            JsonNode payloadNode = rootNode.path("payload");
            String operation = payloadNode.path("op").asText();

            //Now we will extract "before" and "after" from the payload
            JsonNode beforeNode = rootNode.path("payload").path("before");
            JsonNode afterNode = rootNode.path("payload").path("after");

            UserDTO beforeUser = null;
            UserDTO afterUser = null;
            if(beforeNode != null){
                beforeUser = objectMapper.treeToValue(beforeNode, UserDTO.class);
            }
            if(afterNode != null){
                afterUser = objectMapper.treeToValue(afterNode, UserDTO.class);
            }

            User oldUser = null;
            User newUser = null;
            if(beforeUser != null){
                oldUser = convertUserDTOToUser(beforeUser);
            }
            if(afterUser != null){
                newUser = convertUserDTOToUser(afterUser);
            }

            switch(operation) {
                case "c":
                    userService.createUser(newUser);
                    break;
                case "d":
                    assert oldUser != null;
                    userService.deleteUser(oldUser.getUserId());
                    break;
                case "u":
                    userService.updateUser(newUser);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private User convertUserDTOToUser(UserDTO userDto) {
        return User.builder()
                .userId(userDto.getUser_id())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }
}
