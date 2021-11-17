package com.example.taskmanagementapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskmanagementapp.data.dao.TodoDao
import com.example.taskmanagementapp.data.entities.Converter
import com.example.taskmanagementapp.data.entities.TodoData

@Database(entities = [TodoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract  class TodoDataBase  : RoomDatabase(){

    abstract fun todoDao() : TodoDao

    companion object{
        //@volatile means write this field are made visible to other threads
        @Volatile
        private var INSTANCE : TodoDataBase? = null

        fun getDataBase(context: Context): TodoDataBase{
            val tempInstance = INSTANCE

            if(tempInstance != null) return tempInstance else {

                /**
                 * @Synchronisedlock
                 *When a thread calls Synchronised Lock, it acquires the lock of the scynchrozied block
                 * other threads dont have permission to call that same sycnchronised block as long as the
                 * previous thread which had aquired the synchronised lock does not release the lock.
                 *
                 * NOTE:If the synchrozied block is not there, multiple thread can be creating different instances
                 */

                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDataBase::class.java,
                        "todo_table"
                    ).build()
                    INSTANCE = instance
                    return instance
                }

            }
        }
    }
}