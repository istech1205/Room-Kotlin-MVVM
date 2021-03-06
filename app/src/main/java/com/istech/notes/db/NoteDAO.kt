package com.istech.notes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.istech.notes.models.Note

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("Select * from NOTE order by id ASC")
     fun getAllNotes(): LiveData<List<Note>>
}