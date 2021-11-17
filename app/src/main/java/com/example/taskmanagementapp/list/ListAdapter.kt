package com.example.taskmanagementapp.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.entities.Priority
import com.example.taskmanagementapp.data.entities.TodoData
import java.util.zip.Inflater

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var dataList = listOf<TodoData>()

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.todo_title)
        val subTitle: TextView = itemView.findViewById(R.id.sub_title)
        var priority : CardView = itemView.findViewById(R.id.icon_color)
        val layout : ViewGroup = itemView.findViewById(R.id.todo_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.row_selected,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.subTitle.text = dataList[position].description
        var priority  = dataList[position].priority

        when(priority){
            Priority.HIGH -> {
                holder.priority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                R.color.purple_200))
            }
            Priority.MEDIUM -> {
                holder.priority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                    R.color.teal_200))
            }
            Priority.LOW -> {
                holder.priority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                    R.color.green))
            }
        }
        holder.layout.setOnClickListener {
            var todoData = dataList[position]
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(todoData)
            it.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
    return    dataList.size
    }

    fun setData(list : List<TodoData>){
        this.dataList = list
        notifyDataSetChanged()
    }
}