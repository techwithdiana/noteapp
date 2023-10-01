package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.notes.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class NoteHeadingFragment : Fragment() {

    // Creating a variable to hold the number of colums in the RecycleView
    private var columnCount = 1

    // Get the NotesViewModel from the MainActivity
    private val viewModel: MainActivity.NotesViewModel by activityViewModels()

    // This method is called when the fragment is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the colum count from the arguments (if any)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    // When the view is created, this method is called
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the fragment's layout
        val view = inflater.inflate(R.layout.fragment_note_heading_list, container, false)

        // Create an adapter for the RecyclerView using the NotesViewModel
        val adapter:MyNoteHeadingRecyclerViewAdapter=
            MyNoteHeadingRecyclerViewAdapter(AppDatabase.getInstance(requireContext()).
            noteDao().getAll().toMutableList(),viewModel)
        // Set the adapter
        (view as RecyclerView).adapter=adapter

        // Observe changes to the updateDB LiveData and update the adapter accordingly
        viewModel.updateDB.observe(viewLifecycleOwner, Observer {
            adapter.updateFromDB()
        })

        // Return the fragment's view
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            NoteHeadingFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}