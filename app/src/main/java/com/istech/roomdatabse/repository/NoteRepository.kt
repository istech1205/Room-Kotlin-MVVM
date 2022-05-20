package com.istech.roomdatabse.repository

import androidx.lifecycle.LiveData
import com.istech.roomdatabse.db.NoteDAO
import com.istech.roomdatabse.models.Note

class NoteRepository(private val notesDao: NoteDAO) {
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun addNote(note: Note) {
        notesDao.addNote(note)
    }

    suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

}