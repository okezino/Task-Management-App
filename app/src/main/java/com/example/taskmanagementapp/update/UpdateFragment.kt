package com.example.taskmanagementapp.update

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.taskmanagementapp.BaseFragment
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.alertDialog
import com.example.taskmanagementapp.data.entities.Priority
import com.example.taskmanagementapp.data.entities.TodoData
import com.example.taskmanagementapp.data.viewmodel.SharedViewModel
import com.example.taskmanagementapp.data.viewmodel.TodoViewModel
import com.example.taskmanagementapp.databinding.FragmentUpdateBinding


class UpdateFragment(layout : Int = R.layout.fragment_update,menu: Int = R.menu.update_menu) : BaseFragment(layout, menu){

     var binding : FragmentUpdateBinding? = null
     val args : UpdateFragmentArgs by navArgs()



     private val todoViewModel : TodoViewModel by viewModels()
     private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)
        binding!!.UpdateDesc.setText(args.todo.description)
        binding!!.updateEt.setText(args.todo.title)
        binding!!.spinnerUpdate.setSelection(convertPriorityToInt(args.todo.priority))
        binding!!.spinnerUpdate.onItemSelectedListener = sharedViewModel.listener
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.update_todo -> updateTodo()
            R.id.delete_todo -> deleteTodo()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteTodo() {
        alertDialog(requireActivity(),view, { todoViewModel.deleteTodoData(args.todo) })
    }

    private fun updateTodo() {
        var title = binding!!.updateEt.text.toString()
        var dec = binding!!.UpdateDesc.text.toString()
        var priority = binding!!.spinnerUpdate.selectedItem.toString()

        todoViewModel.updateTodoData(TodoData(args.todo.id, title,sharedViewModel.parsePriority(priority),dec))

        Toast.makeText(requireContext(),"updated successful", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.listFragment)
    }

    private fun convertPriorityToInt(priority: Priority) : Int{
       return  when(priority){
            Priority.HIGH -> 2
            Priority.MEDIUM -> 1
            Priority.LOW -> 0
        }
    }


}
