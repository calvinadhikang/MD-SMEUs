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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    private val _responseMessage = MutableLiveData<String>()
    var responseMessage: LiveData<String> = _responseMessage

    fun register(
        name: String,
        email: String,
        phone: String,
        password: String
    ){
        _loading.value = true

        val request: RegisterRequest = RegisterRequest(name, email, phone, password)
        val client = ApiConfig.getApiService().register(request)
        client.enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.code() == 200){
                    _loading.value = false
                    _responseMessage.value = response.body()!!.message
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
    }

    fun setLoading(){
        _loading.value = true
    }
}