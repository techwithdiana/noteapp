package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.notes.databinding.FragmentViewNoteBinding


class ViewNoteFragment : Fragment() {
    private val viewModel: MainActivity.NotesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentViewNoteBinding.inflate(inflater,container, false)
        viewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            binding.textView.text=it.body
            binding.titleTextView.text=it.title
        })
        binding.textView.text=""
        binding.titleTextView.text=""
        return binding.root
    }


}