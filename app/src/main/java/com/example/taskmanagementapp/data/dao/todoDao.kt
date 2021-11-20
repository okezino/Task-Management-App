package com.example.taskmanagementapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskmanagementapp.data.entities.TodoData

@Dao
interface  TodoDao {

    @Query("SELECT *  FROM todo_table ORDER BY id ASC")
    fun getAllData() : LiveData<List<TodoData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodoData(data : TodoData)

    @Update
    suspend fun updateTodoData(data : TodoData)

    @Delete
    suspend fun  deleteTodo(data: TodoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()
}