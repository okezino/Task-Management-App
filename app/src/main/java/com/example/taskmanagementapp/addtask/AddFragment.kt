package com.example.taskmanagementapp.addtask

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanagementapp.BaseFragment
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.data.entities.Priority
import com.example.taskmanagementapp.data.entities.TodoData
import com.example.taskmanagementapp.data.viewmodel.SharedViewModel
import com.example.taskmanagementapp.data.viewmodel.TodoViewModel
import com.example.taskmanagementapp.databinding.FragmentAddBinding


class AddFragment(layout : Int =  R.layout.fragment_add, menu : Int = R.menu.add_item) : BaseFragment(layout, menu){

    var binding : FragmentAddBinding? = null
    private val todoViewModel : TodoViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        binding!!.spinnerAdd.onItemSelectedListener = sharedViewModel.listener
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.add_item_toList){
            insertDataToDataBase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDataBase() {
        val mtitle = binding!!.addEt.text.toString()
        val mdesc = binding!!.addDesc.text.toString()
        val mPriority = binding!!.spinnerAdd.selectedItem.toString()
        if(true){
            val newData =   TodoData(0, mtitle,sharedViewModel.parsePriority(mPriority),mdesc)
            todoViewModel.insertData(newData)

            Toast.makeText(requireContext(),"successful", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"fill out all field", Toast.LENGTH_LONG).show()
        }

    }






}