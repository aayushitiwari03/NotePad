package com.aayushi.notepad.rdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)    // by using primary key we can auto generate the id
    val id: Int=0,
    @ColumnInfo(name = "noteTitle")  // by using columnInfo we can change the name of the column
    val noteTitle:String,
    @ColumnInfo(name = "noteDescription")
    val note_Description:String,
)
