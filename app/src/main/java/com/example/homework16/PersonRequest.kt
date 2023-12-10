package com.example.homework16

data class Person(val email: String,
                  val password: String,
                  val userName:String)

data class PersonRequest(
    val email: String,
    val password: String
){
}

data class RequestResponse(
    val id: Int?,
    val token: String?
)