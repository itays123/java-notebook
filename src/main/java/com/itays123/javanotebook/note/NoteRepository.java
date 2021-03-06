package com.itays123.javanotebook.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "SELECT id, title FROM notes", nativeQuery = true)
    List<NoteTitleAndId> findIdsAndTitles();

    @Query(value = "SELECT id, title FROM notes WHERE user_id = :uid", nativeQuery = true)
    List<NoteTitleAndId> findUserNotes(@Param("uid") Long userId);

}
