package com.example.homework16


import com.squareup.moshi.JsonReader
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkApiService {

    @POST("register")
    suspend fun registerUser(@Body request: PersonRequest): Response<RequestResponse>
    @POST("login")
    suspend fun loginUser(@Body request: PersonRequest): Response<RequestResponse>
}