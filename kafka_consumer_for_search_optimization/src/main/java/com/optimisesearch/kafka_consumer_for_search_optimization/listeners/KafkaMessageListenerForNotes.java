package com.optimisesearch.kafka_consumer_for_search_optimization.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimisesearch.kafka_consumer_for_search_optimization.DTO.NoteDTO;
import com.optimisesearch.kafka_consumer_for_search_optimization.entities.Note;
import com.optimisesearch.kafka_consumer_for_search_optimization.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaMessageListenerForNotes {

    private final NoteService noteService;

    @KafkaListener(topics = "dbserver1.search_optimization_db.notes")
    public void processMessageFromNotes(String message) throws JsonProcessingException {
        System.out.println("This is received message from notes: " + message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(message);

            JsonNode payloadNode = rootNode.path("payload");
            String operation = payloadNode.path("op").asText();


            //Now we will extract "before" and "after" from the payload
            JsonNode beforeNode = rootNode.path("payload").path("before");
            JsonNode afterNode = rootNode.path("payload").path("after");

            NoteDTO beforeNote = null;
            NoteDTO afterNote = null;
            if(beforeNode != null){
                beforeNote = objectMapper.treeToValue(beforeNode, NoteDTO.class);
            }
            if(afterNode != null){
                afterNote = objectMapper.treeToValue(afterNode, NoteDTO.class);
            }

            Note oldNote = null;
            Note newNote = null;
            if(beforeNote != null){
                oldNote = convertNoteDTOToNote(beforeNote);
            }
            if(afterNote != null){
                newNote = convertNoteDTOToNote(afterNote);
            }

            switch(operation) {
                case "c":
                    noteService.createNote(newNote);
                    break;
                case "d":
                    assert oldNote != null;
                    noteService.deleteNote(oldNote.getNoteId());
                    break;
                case "u":
                    noteService.updateNote(newNote);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //================================================================
    private Note convertNoteDTOToNote(NoteDTO noteDto) {

        return Note.builder()
                .noteId(noteDto.getNote_id())
                .noteOwnerId(noteDto.getNote_owner_id())
                .note(noteDto.getNote())
                .build();
    }
}
