package com.example.homework16

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    val moshi  =  Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val networkApiService :NetworkApiService by lazy {
        retrofit.create(NetworkApiService::class.java)
    }
}