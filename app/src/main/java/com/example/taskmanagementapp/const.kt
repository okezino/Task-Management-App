package com.example.taskmanagementapp

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController


fun alertDialog(context : Activity,view: View? = null, func : () -> Unit){
    var builder = AlertDialog.Builder(context).apply {
        setPositiveButton("Yes") { _, _ ->
            func.invoke()
            Toast.makeText(context,"deleted successful", Toast.LENGTH_LONG).show()
            view?.findNavController()?.navigate(R.id.listFragment)
        }
        setNegativeButton("No"){_,_ -> }
        setTitle("Delete All Item")
        setMessage("Are you sure you want to delete All Item?")
    }
    builder.create().show()
}