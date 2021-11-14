package com.example.taskmanagementapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanagementapp.data.entities.TodoData

@Dao
interface  TodoDao {


    @Query("SELECT *  FROM todo_table ORDER BY id ASC")
    fun getAllData() : LiveData<List<TodoData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodoData(data : TodoData)



}