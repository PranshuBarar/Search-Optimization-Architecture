package com.optimisesearch.kafka_consumer_for_search_optimization.listeners;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimisesearch.kafka_consumer_for_search_optimization.DTO.SharingTransactionDTO;
import com.optimisesearch.kafka_consumer_for_search_optimization.entities.SharingTransaction;
import com.optimisesearch.kafka_consumer_for_search_optimization.service.SharingTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class KafkaMessageListenerForSharingTransactions {

    private final SharingTransactionService sharingTransactionService;

    @KafkaListener(topics = "dbserver1.search_optimization_db.shared_notes")
    public void processMessageFromShared_Notes(String message) {
        System.out.println("This is received message from shared_notes: " + message);


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(message);

            JsonNode payloadNode = rootNode.path("payload");
            String operation = payloadNode.path("op").asText();

            //Now we will extract "before" and "after" from the payload
            JsonNode beforeNode = rootNode.path("payload").path("before");
            JsonNode afterNode = rootNode.path("payload").path("after");

            SharingTransactionDTO beforeSharedNote = null;
            SharingTransactionDTO afterSharedNote = null;

            if(beforeNode != null){
                beforeSharedNote = objectMapper.treeToValue(beforeNode, SharingTransactionDTO.class);
            }
            if(afterNode != null){
                afterSharedNote = objectMapper.treeToValue(afterNode, SharingTransactionDTO.class);
            }

            SharingTransaction oldTransaction = null;
            SharingTransaction newTransaction = null;

            if(beforeSharedNote != null){
                oldTransaction = convertSharedTransactionDTOToSharedTransaction(beforeSharedNote);
            }
            if(afterSharedNote != null){
                newTransaction = convertSharedTransactionDTOToSharedTransaction(afterSharedNote);
            }

            switch(operation) {
                case "c":
                    System.out.println("creating shared transaction");
                    sharingTransactionService.createSharingTransaction(newTransaction);
                    break;
                case "d":
                    System.out.println("deleting shared transaction");
                    assert oldTransaction != null;
                    sharingTransactionService.deleteSharingTransaction(oldTransaction.getSharingTransactionId());

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private SharingTransaction convertSharedTransactionDTOToSharedTransaction(SharingTransactionDTO sharingTransactionDTO) {
        return SharingTransaction.builder()
                .sharingTransactionId(sharingTransactionDTO.getSharing_transaction_id())
                .parentNoteId(sharingTransactionDTO.getNote_id())
                .recipientUserId(sharingTransactionDTO.getRecipient_id())
                .build();
    }
}
