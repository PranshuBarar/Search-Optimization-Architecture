package com.optimisesearch.kafka_consumer_for_search_optimization.listeners;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaMessageListenerForSharingTransactions {

    @KafkaListener(topics = "dbserver1.search_optimization_db.shared_notes")
    public void processMessageFromShared_Notes(String message) {
        System.out.println("This is received message from shared_notes: " + message);


    }
}
