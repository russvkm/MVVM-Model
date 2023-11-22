package com.studiokrew.mvvmmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studiokrew.mvvmmodel.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel:DataViewModel by lazy {
        ViewModelProvider(this)[DataViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.data.observe(this) {
            println(it.id)
            println(it.brand)
            println(it.title)
        }

        viewModel.toastString.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }


    }
}