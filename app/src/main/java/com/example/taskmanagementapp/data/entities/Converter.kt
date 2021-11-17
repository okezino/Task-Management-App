package com.example.taskmanagementapp.data.entities

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority) : String{
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String) : Priority{
         return   Priority.valueOf(priority)
    }
}