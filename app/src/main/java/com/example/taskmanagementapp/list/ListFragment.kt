package com.example.taskmanagementapp.list

import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagementapp.BaseFragment
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.alertDialog
import com.example.taskmanagementapp.data.entities.TodoData
import com.example.taskmanagementapp.data.viewmodel.SharedViewModel
import com.example.taskmanagementapp.data.viewmodel.TodoViewModel
import com.example.taskmanagementapp.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment(layout : Int = R.layout.fragment_list, menu : Int = R.menu.list_frag_menu) : BaseFragment(layout,menu){


    private val todoViewModel : TodoViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private var binding : FragmentListBinding? = null

    private  val adapter : ListAdapter by lazy {
        ListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        var recyclerView = binding!!.recyclerViewTodo
        recyclerView.adapter = adapter
        binding!!.lifecycleOwner = this
        binding!!.sharedViewModel = sharedViewModel
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        swipeToDelete(recyclerView)
        todoViewModel.getAllData.observe(viewLifecycleOwner, {
            sharedViewModel.updateTodoItem(it)
            adapter.setData(it)
        })
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.deleteAll -> {
                alertDialog(requireActivity()){ confirmDeleteAll() }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteAll() {
        todoViewModel.deleteAllTodoData()
    }

    private fun confirmDeleteOneItem(todoData: TodoData) {
        todoViewModel.deleteTodoData(todoData)
    }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
              val todoData = adapter.dataList[viewHolder.adapterPosition]
                alertDialog(requireActivity()){confirmDeleteOneItem(todoData)}
            }
        }

        val itemTouchHelper =  ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        adapter.notifyDataSetChanged()
    }


}