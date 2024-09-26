package com.optimisesearch.kafka_consumer_for_search_optimization.repo;

import com.optimisesearch.kafka_consumer_for_search_optimization.entities.Note;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends ElasticsearchRepository<Note,Integer> {
    Note findByNoteId(int noteId);
    void deleteByNoteId(int noteId);
}
