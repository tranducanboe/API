package com.example.retrofitcallapi1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("photos")
    fun getdata(): Call<List<DataItem>>
    @POST("photos")
    fun postdata(@Body post: DataItem): Call<DataItem>
}