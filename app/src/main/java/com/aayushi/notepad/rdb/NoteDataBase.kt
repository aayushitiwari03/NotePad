package com.aayushi.notepad.rdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)   //this is the database class
abstract class NoteDataBase:RoomDatabase() {

    abstract fun getNoteDao(): NoteDao  //this function is used to get the NoteDao

    companion object{
        @Volatile   //this annotation is used to make the instance visible to other threads
        private var INSTANCE:NoteDataBase?=null //this is the instance of NoteDataBase class

        fun getInstance(context: Context):NoteDataBase{
            synchronized(this){ //this is used to make the instance thread safe
                var instance= INSTANCE //this will assign the INSTANCE to instance
                if(instance==null){ //if instance is null then we will create the instance
                    instance = Room.databaseBuilder(    //this is used to create the instance
                        context.applicationContext, //this is the context of application
                        NoteDataBase::class.java,   //this is the class of database
                        "note_database" //this is the name of the database
                    ).build() //this is used to build the database
                    INSTANCE=instance //this will assign the instance to INSTANCE
                }
                return instance //this will return the instance


            }
        }
    }
}