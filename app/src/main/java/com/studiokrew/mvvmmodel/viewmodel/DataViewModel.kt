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
    private var _data = MutableLiveData<ScreenState<ApiDataModal>>()

    val data:LiveData<ScreenState<ApiDataModal>> get() = _data


    init {
        fetchData()
    }

    private fun fetchData(){
        val call = repository.getCharacters()
        _data.postValue(ScreenState.Loading(null))
        call.enqueue(object : Callback<ApiDataModal>{
            override fun onResponse(call: Call<ApiDataModal>, response: Response<ApiDataModal>) {
                if(response.isSuccessful){
                    _data.postValue(ScreenState.Success(response.body()))
                }
            }

            override fun onFailure(call: Call<ApiDataModal>, t: Throwable) {
                _data.postValue(ScreenState.Error(t.message.toString(), null))
            }
        })
    }
}