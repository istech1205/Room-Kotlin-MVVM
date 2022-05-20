package com.istech.roomdatabse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.istech.roomdatabse.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }
}