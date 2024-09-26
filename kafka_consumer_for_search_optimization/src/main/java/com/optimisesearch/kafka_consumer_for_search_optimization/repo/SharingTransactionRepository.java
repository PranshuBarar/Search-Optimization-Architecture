package com.optimisesearch.kafka_consumer_for_search_optimization.repo;

import com.optimisesearch.kafka_consumer_for_search_optimization.entities.Note;
import com.optimisesearch.kafka_consumer_for_search_optimization.entities.SharingTransaction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharingTransactionRepository extends ElasticsearchRepository<SharingTransaction, Integer> {
    Note findBySharingTransactionId(int sharingTransactionId);
    void deleteBySharingTransactionId(int sharingTransactionId);
}
