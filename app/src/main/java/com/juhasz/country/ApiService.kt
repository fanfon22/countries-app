package com.juhasz.country

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    fun getUsers(): Call<ArrayList<UserModel>>
}