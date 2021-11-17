package com.example.taskmanagementapp.data.repository

import androidx.lifecycle.LiveData
import com.example.taskmanagementapp.data.dao.TodoDao
import com.example.taskmanagementapp.data.entities.TodoData

class TodoRepository(private val todoDao: TodoDao) {

    val getAllData : LiveData<List<TodoData>> = todoDao.getAllData()


   suspend fun insertData (todoData: TodoData){
        todoDao.insertTodoData(todoData)
    }
}