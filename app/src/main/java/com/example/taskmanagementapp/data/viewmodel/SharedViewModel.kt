package com.example.taskmanagementapp.data.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.entities.Priority
import com.example.taskmanagementapp.data.entities.TodoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(application: Application): AndroidViewModel(application) {
    var  todoItems  : MutableLiveData<Boolean> = MutableLiveData(false)
    val  _todoItems : LiveData<Boolean>
        get() = todoItems



    fun updateTodoItem(data : List<TodoData>){
        todoItems.value = data.isEmpty()
    }


    val listener : AdapterView.OnItemSelectedListener = object :
    AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long) {

            when(position){
                0 -> {
                    (parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                        R.color.yellow))
                }
                1 -> {
                    (parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                        R.color.green))
                }
                2 -> {
                    (parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                        R.color.red))
                }
            }

        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

    }
    fun parsePriority(priority: String) : Priority {

        return   when(priority){
            "High" -> Priority.HIGH
            "Medium" -> Priority.MEDIUM
            "Low" -> Priority.LOW
            else -> Priority.LOW
        }
    }


}