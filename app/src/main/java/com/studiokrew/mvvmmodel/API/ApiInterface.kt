package com.studiokrew.mvvmmodel.API

import com.studiokrew.mvvmmodel.modal.ApiDataModal
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object ApiClient{
    // api base url
    val baseUrl = "https://dummyjson.com/"

    // MARK: - create retrofit call
    private val retrofit:Retrofit by lazy {
            Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // create retrofit call
    val apiService:ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}
interface ApiInterface {
    @GET("products/{id}")
    fun groupList(@Path("id") groupId: Int): Call<ApiDataModal>
}