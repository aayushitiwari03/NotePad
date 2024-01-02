package com.aayushi.notepad.db

import androidx.room.Entity

@Entity(tableName = "notes")
data class Notes(
    val id: Int,
    val noteTitle:String,
    val note_Description:String,
)
