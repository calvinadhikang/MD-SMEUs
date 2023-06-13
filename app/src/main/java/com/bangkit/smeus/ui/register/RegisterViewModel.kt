package com.bangkit.smeus.ui.register

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.api.RegisterResponse
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
        password: String,
        context: Context
    ){
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
                    val message = response.body()!!.message

                    _responseMessage.value = message
                    Log.e("REGISTER", message)
                    Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show()

                    if (message == "berhasil register!"){
                        val activity = context as Activity
                        activity.finish()
                    }
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
}