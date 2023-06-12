package com.bangkit.smeus.ui.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.bangkit.smeus.ui.api.response.SMEResponse
import com.bangkit.smeus.ui.api.response.SMEResponseItem
import com.example.storyapp.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmeRepository {
    fun getSMEs(): List<SMEResponseItem> {
        var data: List<SMEResponseItem> = listOf()

        val client = ApiConfig.getApiService().fetchSMEs()
        client.enqueue(object: Callback<SMEResponse> {
            override fun onResponse(call: Call<SMEResponse>, response: Response<SMEResponse>) {
                if (response.isSuccessful){
                    data = response.body()!!.sMEResponse as List<SMEResponseItem>
                    Log.e("data", data[0].city!!)
                }
            }

            override fun onFailure(call: Call<SMEResponse>, t: Throwable) {

            }
        })

        return data
    }
}