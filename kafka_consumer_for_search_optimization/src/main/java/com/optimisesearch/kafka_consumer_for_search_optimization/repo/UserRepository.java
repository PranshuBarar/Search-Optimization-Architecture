package com.optimisesearch.kafka_consumer_for_search_optimization.repo;

import com.optimisesearch.kafka_consumer_for_search_optimization.entities.Note;
import com.optimisesearch.kafka_consumer_for_search_optimization.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User,Integer> {
    Note findByUserId(int userId);
    void deleteByUserId(int userId);
}
