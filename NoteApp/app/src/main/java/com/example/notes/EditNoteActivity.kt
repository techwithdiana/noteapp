package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.databinding.ActivityEditNoteBinding
import com.example.notes.databinding.ActivityMainBinding


class EditNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val notes = AppDatabase.getInstance(this).noteDao().getAll()
        Log.i("Notes",notes.toString())
        binding.saveButton.setOnClickListener {
            Log.i("Title",binding.titleTextView.text.toString())
            var newNote = Note(binding.titleTextView.text.toString(),binding.bodyEditText.text.toString())
            AppDatabase.getInstance(this).noteDao().insert(newNote)
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}