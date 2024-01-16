package com.aayushi.notepad.repo


import com.aayushi.notepad.rdb.NoteDataBase
import com.aayushi.notepad.rdb.Notes
import kotlinx.coroutines.flow.Flow

class NoteRepository (private val noteDataBase: NoteDataBase){ //this class is used to perform the operation on database

        fun getAllNotes(): Flow<List<Notes> >{
            return noteDataBase.getNoteDao().getAllNotes() //this function will return all the notes from database
        }

        suspend fun insertNote (note: Notes){
            noteDataBase.getNoteDao().insertNote (note) //this function will insert the note in database
        }

        suspend fun updateNote (note: Notes){
            noteDataBase.getNoteDao().updateNotes (note) //this function will update the note in database
        }

        suspend fun deleteNote (note: Notes){
            noteDataBase.getNoteDao().deleteNotes (note) //this function will delete the note from database
        }

        suspend fun deleteAllNotes (){
            noteDataBase.getNoteDao().deleteAllNotes() //this function will delete all the notes from database
        }

}