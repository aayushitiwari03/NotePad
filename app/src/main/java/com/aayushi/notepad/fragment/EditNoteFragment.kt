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
import com.aayushi.notepad.databinding.FragmentEditNoteBinding
import com.aayushi.notepad.rdb.NoteDataBase
import com.aayushi.notepad.rdb.Notes
import com.aayushi.notepad.repo.NoteRepository
import com.aayushi.notepad.viewmodel.NoteViewModel
import com.google.gson.Gson

class EditNoteFragment : Fragment() {
    lateinit var binding: FragmentEditNoteBinding
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
            R.layout.fragment_edit_note,
            container,
            false
        )
        database = NoteDataBase.getInstance(requireContext())
        repository = NoteRepository(database)
        viewModelFactory = NotesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        return binding.root
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnInitialData()
    }

    private fun setOnInitialData() {
        val jsonNote = arguments?.getString("note")
        if (jsonNote != null) {
            val note = Gson().fromJson(jsonNote, Notes::class.java)
            binding.updateNoteTitle.setText(note.noteTitle)
            binding.updateNoteDescription.setText(note.note_Description)
            binding.updateNote.setOnClickListener {
                viewModel.updateNotes(
                    Notes(
                        id = note.id,
                        noteTitle = binding.updateNoteTitle.text.toString(),
                        note_Description = binding.updateNoteDescription.text.toString()
                    )
                )

                Toast.makeText(requireContext(), "Note Updated", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).findNavController(R.id.nav_host_graph)
                    .navigate(R.id.action_editNoteFragment_to_displayNotesFragment)
            }
        }
    }

}