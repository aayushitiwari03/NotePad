package com.aayushi.notepad.rdb

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes)     // suspend function is used to perform the operation in background thread and Insert is used to insert the data in database

    @Update
    suspend fun updateNotes(note: Notes)  //Update is used to update the Inserted data in database

    @Delete
    suspend fun deleteNotes(note: Notes)    //Delete is used to delete the data from database

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Notes>>    //this query will return all the data from database

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()    //this query is used to delete all the data from database


}