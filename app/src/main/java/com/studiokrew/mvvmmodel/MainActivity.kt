package com.studiokrew.mvvmmodel

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.studiokrew.mvvmmodel.databinding.ActivityMainBinding
import com.studiokrew.mvvmmodel.modal.ApiDataModal
import com.studiokrew.mvvmmodel.viewmodel.DataViewModel
import com.studiokrew.mvvmmodel.viewmodel.ScreenState

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var progressDialog:ProgressDialog

    private val viewModel:DataViewModel by lazy {
        ViewModelProvider(this)[DataViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        viewModel.data.observe(this) { state->
            screenState(state)
        }


    }

    private fun screenState(state:ScreenState<ApiDataModal>){
        when(state){
            is ScreenState.Loading -> {
                progressDialog.setMessage("Please wait....")
                progressDialog.show()
            }

            is ScreenState.Success ->{
                progressDialog.dismiss()
                binding.viewModel = state.data
            }

            is ScreenState.Error -> {
                progressDialog.dismiss()
                Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}