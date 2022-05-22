package com.istech.notes
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.istech.notes.databinding.ActivityAddNoteBinding
import com.istech.notes.models.Note
import com.istech.notes.utils.Const
import com.istech.notes.vm.NotesVM
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private val notesVM: NotesVM by viewModels()
    private lateinit var context: Context
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        context = binding.root.context
        initObserver()
        initData()
    }

    private fun initObserver() {
        val type = intent.getStringExtra(Const.type)
        when (type) {
            Const.edit -> {
                isUpdate = true
                setData()
            }
            Const.add -> {
                isUpdate = false

            }

        }
    }

    private fun setData() {
        val key = intent.getStringExtra(Const.data)
        notesVM.note = Gson().fromJson(key, Note::class.java)
        binding.etTitle.setText(notesVM.note?.title)
        binding.etDesc.setText(notesVM.note?.description)
        binding.btAddNote.text = "Update Note"
    }


    private fun initData() {

        binding.apply {
            btAddNote.setOnClickListener {
                if (isUpdate) {
                    updateNote()
                } else {
                    addNote()
                }
                startActivity(Intent(context, MainActivity::class.java))
                finishAffinity()

            }
        }
    }

    private fun addNote() {
        val title = binding.etTitle.text.toString()
        val desc = binding.etDesc.text.toString()
        if (title.isEmpty()) {
            binding.etTitle.error = "Enter title"
            binding.etTitle.requestFocus()
        } else if (desc.isEmpty()) {
            binding.etDesc.error = "Enter Description"
            binding.etDesc.requestFocus()
        } else {
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH::mm")
            val currentDate = sdf.format(Date())
            val note = Note(
                binding.etTitle.text.toString(),
                binding.etDesc.text.toString(),
                currentDate
            )
            notesVM.addNote(note)
            Const.toast("Added", context)
        }
    }

    private fun updateNote() {
        val title = binding.etTitle.text.toString()
        val desc = binding.etDesc.text.toString()
        if (title.isEmpty()) {
            binding.etTitle.error = "Enter title"
            binding.etTitle.requestFocus()
        } else if (desc.isEmpty()) {
            binding.etDesc.error = "Enter Description"
            binding.etDesc.requestFocus()
        } else {
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH::mm")
            val currentDate = sdf.format(Date())
            val note = Note(
                binding.etTitle.text.toString(),
                binding.etDesc.text.toString(),
                currentDate,
                notesVM.note?.id!!
            )
            notesVM.updateNote(note)
            Const.toast("Updated", context)
        }
    }
}