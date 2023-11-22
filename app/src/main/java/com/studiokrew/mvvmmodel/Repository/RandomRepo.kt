package com.studiokrew.mvvmmodel.Repository

import com.studiokrew.mvvmmodel.API.ApiInterface

class RandomRepo(private val apiService: ApiInterface) {
    var random = (1..15).random()

    //get data from api
    fun getCharacters() = apiService.groupList(random)
}