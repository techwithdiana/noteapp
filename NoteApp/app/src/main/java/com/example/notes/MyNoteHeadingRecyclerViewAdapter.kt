package com.example.notes

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels

import com.example.notes.placeholder.PlaceholderContent.PlaceholderItem
import com.example.notes.databinding.FragmentNoteHeadingBinding
import com.example.notes.databinding.FragmentNoteHeadingListBinding
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatterBuilder

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

class MyNoteHeadingRecyclerViewAdapter(
    private val values: MutableList<Note>, //declaring a private property for the list of notes to display
    private val viewModel: MainActivity.NotesViewModel // Declaring a property for the ViewModel

) : RecyclerView.Adapter<MyNoteHeadingRecyclerViewAdapter.ViewHolder>() {

    // Initialize a SimpleDateFormat object for the formatting the date
    val formatter = SimpleDateFormat("yy/MM/dd HH:mm:ss")

    private lateinit var parent:ViewGroup

    // Create a ViewHolder for the RecyclerView
    override fun onCreateViewHolder(_parent: ViewGroup, viewType: Int): ViewHolder {

        parent = _parent // STore a reference to the parent view
        return ViewHolder(
            FragmentNoteHeadingBinding.inflate( // Inflate the layout for the ViewHolder
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    // Public method to update the list of notes from the database
    public fun updateFromDB()
    {
        if(this::parent.isInitialized) {
            this.values.clear() // Clear the list of notes

            this.values.addAll(0, AppDatabase.getInstance(parent.context).noteDao().getAll())
            notifyDataSetChanged() // Notify the adapter that the data set has changed
        }
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position] // Get the current note
        holder.idView.text = item.title // Set the title of the note in the ViewHolder
        holder.contentView.text = formatter.format(item.created) // Set the creation date of the note in the ViewHolder
        holder.deleteButton.setOnClickListener {// Set a click listener on the delete button
            AppDatabase.getInstance(holder.idView.context).noteDao().deleteById(item.uid) // Delete the note from the database
            updateFromDB()
            if(item.uid== viewModel.selectedItem.value?.uid ) // If the deleted note is the currently selected note
            {
                viewModel.selectItem(Note("")) // Clear the selected note from the ViewModel
            }
        }
        holder.itemView.setOnClickListener {// Set a click listener on the ViewHolder
            viewModel.selectItem(item) // Set the selected note in the ViewModel
        }
    }

    // Return the number of items in the list of notes
    override fun getItemCount(): Int = values.size

    // Define a ViewHolder class for the RecyclerView
    inner class ViewHolder(binding: FragmentNoteHeadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber // Declare a TextView for the note title
        val contentView: TextView = binding.content // Declare a TextView for the note creation date
        val deleteButton: ImageButton = binding.imageButton // Declare an ImageButton for the delete button
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'" // Return a string representation of the ViewHolder
        }
    }

}