package com.example.taskmanagementapp.data.entities

import android.content.ClipDescription
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoData(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val priority: Priority,
    val description: String
)