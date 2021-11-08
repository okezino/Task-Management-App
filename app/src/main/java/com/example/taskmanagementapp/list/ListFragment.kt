package com.example.taskmanagementapp.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanagementapp.BaseFragment
import com.example.taskmanagementapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment(layout : Int = R.layout.fragment_list, menu : Int = R.menu.list_frag_menu) : BaseFragment(layout,menu){
    lateinit var floatBtn : FloatingActionButton
    lateinit var layoutbr : ViewGroup
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatBtn = view.findViewById(R.id.floatingActionButton)
        layoutbr = view.findViewById(R.id.listLayout)


        floatBtn.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        layoutbr.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }
    }


}