package com.optimisesearch.kafka_consumer_for_search_optimization.service;

import com.optimisesearch.kafka_consumer_for_search_optimization.entities.User;
import com.optimisesearch.kafka_consumer_for_search_optimization.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(int noteId){
        userRepository.deleteByUserId(noteId);
    }

    public void updateUser(User userForUpdate){
        userRepository.save(userForUpdate);
    }
}
