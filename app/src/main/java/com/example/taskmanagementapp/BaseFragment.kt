package com.example.taskmanagementapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment


open class BaseFragment(private val layout : Int,private val menu_tag: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(layout, container, false)
        setHasOptionsMenu(true)
        return  view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(menu_tag, menu)

    }




}