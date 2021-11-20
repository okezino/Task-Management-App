package com.example.taskmanagementapp.binding

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.entities.Priority
import com.example.taskmanagementapp.data.entities.TodoData
import com.example.taskmanagementapp.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton


@BindingAdapter("navigateToAddFragment")
fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
    view.setOnClickListener {
        if (navigate) {
            view.findNavController().navigate(R.id.addFragment)
        }
    }
}

@BindingAdapter("showEmptyViews")
fun showEmptyViews(view: View, data: LiveData<Boolean>) {
    when (data.value) {
        true -> view.visibility = VISIBLE
        false -> view.visibility = GONE
    }

}

@BindingAdapter("parsePriorityToInt")
fun parsePriorityToInt(view: Spinner, priority: Priority) {
  when(priority){
      Priority.LOW -> view.setSelection(0)
      Priority.MEDIUM -> view.setSelection(1)
      Priority.HIGH -> view.setSelection(2)
  }

}

@BindingAdapter("parsePriorityColor")
fun parsePriorityColor(view:CardView, priority: Priority){
    when(priority){
        Priority.LOW -> {view.setCardBackgroundColor(view.context.resources.getColor(R.color.yellow))}
        Priority.MEDIUM -> {view.setCardBackgroundColor(view.context.resources.getColor(R.color.green))}
        Priority.HIGH -> {view.setCardBackgroundColor(view.context.resources.getColor(R.color.red))}
    }
}

@BindingAdapter("sendDataToUpdateFragment")
fun sendDataToUpdateFragment(view : ConstraintLayout, todoData: TodoData){
    view.setOnClickListener {
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(todoData)
        view.findNavController().navigate(action)
    }
}
