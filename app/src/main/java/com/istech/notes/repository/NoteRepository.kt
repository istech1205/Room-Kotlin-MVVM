package com.istech.notes.repository

import androidx.lifecycle.LiveData
import com.istech.notes.db.NoteDAO
import com.istech.notes.models.Note

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