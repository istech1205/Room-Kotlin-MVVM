package com.istech.notes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.istech.notes.adapter.NotesAdapter
import com.istech.notes.databinding.ActivityMainBinding
import com.istech.notes.models.Note
import com.istech.notes.utils.Const
import com.istech.notes.vm.NotesVM

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val notesAdapter = NotesAdapter()
    private val notesVM: NotesVM by viewModels()
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        context = binding.root.context
        initData()
        initObserver()
    }

    private fun initObserver() {
        notesAdapter.onClick = object : NotesAdapter.OnClick {
            override fun onClickHolder(model: Note, type: Int) {
                when (type) {
                    0 -> {
                        notesVM.deleteNote(model)
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        val intent = Intent(context, AddNoteActivity::class.java)
                        intent.putExtra(Const.type, Const.edit)
                        intent.putExtra(Const.data, Gson().toJson(model))
                        startActivity(intent)
                    }
                }
            }

        }
    }

    private fun initData() {
        binding.rvNotes.adapter = notesAdapter
        notesVM.allNotes.observe(this) {
            if (it != null) {
                notesAdapter.updateData(it)
            }
        }
        handleClick()
    }

    private fun handleClick() {
        binding.addNote.setOnClickListener {
            val intent = Intent(context, AddNoteActivity::class.java)
            intent.putExtra(Const.type, Const.add)
            startActivity(intent)
        }
    }
}