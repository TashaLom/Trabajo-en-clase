package com.example.login
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitclient {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val api: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}