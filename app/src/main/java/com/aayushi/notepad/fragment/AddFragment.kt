package com.aayushi.notepad.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.aayushi.notepad.activity.MainActivity
import com.aayushi.notepad.viewmodel.NotesViewModelFactory
import com.aayushi.notepad.R
import com.aayushi.notepad.databinding.FragmentAddBinding
import com.aayushi.notepad.rdb.NoteDataBase
import com.aayushi.notepad.rdb.Notes
import com.aayushi.notepad.repo.NoteRepository
import com.aayushi.notepad.viewmodel.NoteViewModel


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    lateinit var viewModel: NoteViewModel
    lateinit var repository: NoteRepository
    lateinit var database: NoteDataBase
    private lateinit var viewModelFactory : NotesViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_add,
            container,
            false
        )
        database = NoteDataBase.getInstance(requireContext())
        repository = NoteRepository(database)
        viewModelFactory = NotesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        binding.saveNote.setOnClickListener {
            viewModel.insertNote(
                Notes(
                    noteTitle = binding.addNoteTitle.text.toString(),
                    note_Description = binding.addNoteDescription.text.toString()
                )
            )
            Toast.makeText(requireContext(), "Note Saved", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).findNavController(R.id.nav_host_graph)
                .navigate(R.id.action_addFragment_to_displayNotesFragment)
        }

        return binding.root
    }

}