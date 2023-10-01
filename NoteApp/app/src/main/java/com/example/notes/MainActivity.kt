package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // NotesViewModel sub-class inherited from ViewModel base-class
    class NotesViewModel:ViewModel()
    {
        //Initializing mutableSelectedItem to MutableLiveData<Note>
        //to store the currently selected item
        private val mutableSelectedItem = MutableLiveData<Note>()

        //Initializing mutUpdateDB to MutableLiveData<Int>
        // to indicate when the database needs to be updated
        private val mutUpdateDB = MutableLiveData<Int>()

        // Accessors
        val selectedItem: LiveData<Note> get() = mutableSelectedItem
        val updateDB:LiveData<Int> get()=mutUpdateDB


        //Function to set the selected Cat
        fun selectItem(item: Note) {
            mutableSelectedItem.value = item
        }
        fun  selectUpdateDB(value:Int)
        {
            mutUpdateDB.value = value
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this,EditNoteActivity::class.java))
        }
        setContentView(binding.root)

    }
}