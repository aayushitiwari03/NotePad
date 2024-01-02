package com.aayushi.notepad.db

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes)

    @Update
    suspend fun updateNotes(note: Notes)

    @Delete
    suspend fun deleteNotes(note: Notes)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Notes>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()


}