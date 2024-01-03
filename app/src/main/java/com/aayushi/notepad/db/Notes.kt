package com.aayushi.notepad.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle:String,
    val note_Description:String,
)
