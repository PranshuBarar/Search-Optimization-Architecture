package com.optimisesearch.kafka_consumer_for_search_optimization.service;

import com.optimisesearch.kafka_consumer_for_search_optimization.entities.SharingTransaction;
import com.optimisesearch.kafka_consumer_for_search_optimization.repo.SharingTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SharingTransactionService {
    private final SharingTransactionRepository sharingTransactionRepository;

    public void createSharingTransaction(SharingTransaction sharingTransaction){
        sharingTransactionRepository.save(sharingTransaction);
    }

    public void deleteSharingTransaction(int sharingTransactionId){
        sharingTransactionRepository.deleteBySharingTransactionId(sharingTransactionId);
    }
}
