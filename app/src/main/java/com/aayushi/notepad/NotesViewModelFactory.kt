package com.aayushi.notepad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aayushi.notepad.fragment.NoteViewModel
import com.aayushi.notepad.repo.NoteRepository

class NotesViewModelFactory(private val repository: NoteRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    //this class is used to create the instance of NoteViewModel class
}