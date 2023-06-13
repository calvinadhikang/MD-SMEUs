package com.bangkit.smeus.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.api.RegisterRequest
import com.bangkit.smeus.ui.api.RegisterResponse
import com.bangkit.smeus.ui.api.response.SMEResponse
import com.bangkit.smeus.ui.api.response.SMEResponseItem
import com.example.storyapp.api.ApiConfig
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _responseMessage = MutableStateFlow("")
    val responseMessage: StateFlow<String> get() = _responseMessage

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun register(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Boolean {
        var success = false
        _loading.value = true

        val client = ApiConfig.getApiService().register(name, email, phone, password)
        client.enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    _loading.value = false
                    _responseMessage.value = response.body()!!.message
                    Log.e("REGISTER", response.body()!!.message)

                    success = true
                }else{
                    val gson = GsonBuilder().create()
                    try {
                        var pojo = gson.fromJson(response.errorBody().toString(), RegisterResponse::class.java)
                        _responseMessage.value = pojo.message
                    } catch (ex: Exception) {
                        Log.e("REGISTER ERR", ex.message.toString())
                        _loading.value = true
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            }
        })

        return success
    }
}