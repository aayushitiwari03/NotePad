package com.aayushi.notepad.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aayushi.notepad.activity.MainActivity
import com.aayushi.notepad.viewmodel.NotesViewModelFactory
import com.aayushi.notepad.R
import com.aayushi.notepad.adapter.NotesAdapter
import com.aayushi.notepad.databinding.FragmentDisplayNotesBinding
import com.aayushi.notepad.rdb.NoteDataBase
import com.aayushi.notepad.rdb.Notes
import com.aayushi.notepad.repo.NoteRepository
import com.aayushi.notepad.viewmodel.NoteViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisplayNotesFragment : Fragment() {

    lateinit var binding: FragmentDisplayNotesBinding
    lateinit var adapter: NotesAdapter
    lateinit var noteViewModel: NoteViewModel
    lateinit var repository: NoteRepository
    lateinit var database: NoteDataBase
    lateinit var noteViewModelFactory: NotesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_display_notes,
            container,
            false
        )
        database = NoteDataBase.getInstance(requireContext())
        repository = NoteRepository(database)
        noteViewModelFactory = NotesViewModelFactory(repository)

        binding.floatingActionButton2.setOnClickListener {
            (activity as MainActivity).findNavController(R.id.nav_host_graph)
                .navigate(R.id.action_displayNotesFragment_to_addFragment)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        noteViewModel = ViewModelProvider(this, noteViewModelFactory)[NoteViewModel::class.java]

        adapter = NotesAdapter(
            emptyList(),
            onDeleteClickListener = { note -> deleteNotes(note) },
            onNoteClick = { note -> onNoteClick(note) }
        )

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            noteViewModel.notes.collect { newNotes ->
                adapter.updateData(newNotes)
            }
        }


        return binding.root
    }

    private fun onNoteClick(note: Notes){
        val bundle = Bundle()
        bundle.putString("note", Gson().toJson(note))
        (activity as MainActivity).findNavController(R.id.nav_host_graph)
            .navigate(R.id.action_displayNotesFragment_to_editNoteFragment, bundle)
    }

    private fun deleteNotes(note: Notes) {
        CoroutineScope(Dispatchers.IO).launch {
            noteViewModel.deleteNotes(note)

        }
    }

}