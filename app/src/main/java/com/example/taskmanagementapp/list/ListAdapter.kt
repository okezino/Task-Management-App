package com.example.taskmanagementapp.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.entities.Priority
import com.example.taskmanagementapp.data.entities.TodoData
import com.example.taskmanagementapp.databinding.RowSelectedBinding
import java.util.zip.Inflater

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var dataList = listOf<TodoData>()

    class ListViewHolder(private val binding : RowSelectedBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(todoData: TodoData){
            binding.todoData = todoData
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent : ViewGroup) : ListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowSelectedBinding.inflate(layoutInflater,parent,false)
                return ListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
    return    dataList.size
    }

    fun setData(list : List<TodoData>){
        val diffUtilClass = DiffUtilClass(dataList, list)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilClass)
        this.dataList = list
        diffUtilResult.dispatchUpdatesTo(this)
    }
}