package com.example.homework16

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel : ViewModel() {
    private val _response = MutableStateFlow<Response<RequestResponse>?>(null)
    val response: StateFlow<Response<RequestResponse>?> = _response.asStateFlow()

    var connectionError = false

    fun registerPerson(personRequest: PersonRequest){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Network.networkApiService.registerUser(personRequest)
                _response.value = response
                connectionError = false
            } catch (e: Exception) {
                Log.d("tag123","${e.toString()}")
                _response.value = null
                connectionError = true
            }
        }
    }

    fun loginPerson(personRequest: PersonRequest){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Network.networkApiService.loginUser(personRequest)
                _response.value = response
                connectionError = false
            } catch (e: Exception) {
                Log.d("tag123","${e.toString()}")
                _response.value = null
                connectionError = true
            }
        }
    }

}