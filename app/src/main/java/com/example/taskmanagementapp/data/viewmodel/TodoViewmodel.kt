package com.example.taskmanagementapp.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmanagementapp.data.database.TodoDataBase
import com.example.taskmanagementapp.data.entities.TodoData
import com.example.taskmanagementapp.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoDao = TodoDataBase.getDataBase(application).todoDao()
    private val repository : TodoRepository
     val getAllData : LiveData<List<TodoData>>

    init {
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData
    }

    fun insertData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO) {
           try {
               repository.insertData(todoData)
               Log.i("ViewModel","reached")
           }catch (e : Exception){
               throw e
           }
        }
    }

    fun updateTodoData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO) {
          try {
              repository.updateData(todoData)
          }catch (e : Exception){
              throw  e
          }
        }
    }

    fun deleteTodoData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteData(todoData)
            }catch (e : Exception){
                throw  e
            }
        }
    }

    fun deleteAllTodoData(){
        viewModelScope.launch {
            try {
                repository.deleteAll()
            }catch (e : Exception){
                throw  e
            }
        }
    }



}