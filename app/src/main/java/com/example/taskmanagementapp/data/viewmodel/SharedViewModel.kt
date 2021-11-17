package com.example.taskmanagementapp.data.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.entities.Priority

class SharedViewModel(application: Application): AndroidViewModel(application) {
    val listener : AdapterView.OnItemSelectedListener = object :
    AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long) {

            when(position){
                0 -> {
                    (parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                        R.color.green))
                }
                1 -> {
                    (parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                        R.color.purple_200))
                }
                2 -> {
                    (parent!!.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                        R.color.teal_200))
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