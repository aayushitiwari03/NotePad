package com.aayushi.notepad.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayushi.notepad.rdb.Notes
import com.aayushi.notepad.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel(){

    private val _notes = MutableStateFlow<List<Notes>>(emptyList()) //this is used to store the notes
    val notes:StateFlow<List<Notes>> = _notes //this is used to get the notes

    init {
        getAllNotes() //this function will get all the notes from database
    }

     fun getAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _notes.value = noteRepository.getAllNotes().first()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

     fun insertNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                noteRepository.insertNote (notes)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

     fun updateNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                noteRepository.updateNote (notes)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

     fun deleteNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                noteRepository.deleteNote (notes)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

     fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                noteRepository.deleteAllNotes ()
                _notes.value = emptyList()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}