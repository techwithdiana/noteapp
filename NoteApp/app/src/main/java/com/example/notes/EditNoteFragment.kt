package com.example.notes

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.notes.databinding.FragmentEditNoteBinding
import kotlin.random.Random


class EditNoteFragment : Fragment() {

    // Declare binding and NotesViewModel properties
    private lateinit var binding:FragmentEditNoteBinding
    private val viewModel: MainActivity.NotesViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(inflater,container,false)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}