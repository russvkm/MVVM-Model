package com.studiokrew.mvvmmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiokrew.mvvmmodel.API.ApiClient
import com.studiokrew.mvvmmodel.Repository.RandomRepo
import com.studiokrew.mvvmmodel.modal.ApiDataModal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataViewModel(private val repository:RandomRepo = RandomRepo(apiService = ApiClient.apiService)):ViewModel() {
    private var _data = MutableLiveData<ApiDataModal>()
    private var _toastString = MutableLiveData<String>()

    val data:LiveData<ApiDataModal> get() = _data

    val toastString:LiveData<String> get() = _toastString

    init {
        fetchData()
    }

    private fun fetchData(){
        val call = repository.getCharacters()
        call.enqueue(object : Callback<ApiDataModal>{
            override fun onResponse(call: Call<ApiDataModal>, response: Response<ApiDataModal>) {
                if(response.isSuccessful){
                    _data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ApiDataModal>, t: Throwable) {
                _toastString.postValue(t.message.toString())
            }
        })
    }
}