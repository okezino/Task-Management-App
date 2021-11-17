package com.example.taskmanagementapp.data.entities

import android.content.ClipDescription
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class TodoData(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val priority: Priority,
    val description: String
) : Parcelable