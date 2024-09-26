package com.optimisesearch.kafka_consumer_for_search_optimization.service;

import com.optimisesearch.kafka_consumer_for_search_optimization.entities.Note;
import com.optimisesearch.kafka_consumer_for_search_optimization.repo.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public void createNote(Note note){
        noteRepository.save(note);
    }

    public void deleteNote(int noteId){
        noteRepository.deleteByNoteId(noteId);
    }

    public void updateNote(Note noteForUpdate){
        noteRepository.save(noteForUpdate);
    }

}
