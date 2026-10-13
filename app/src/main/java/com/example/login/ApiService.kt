package com.example.login

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts/{id}")
    suspend fun getData(
        @Path("id") id: Int
    ): Data
}