package com.istech.notes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val description: String,
    val timestamp: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

)
