package com.bangkit.smeus.ui.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.bangkit.smeus.ui.api.DetailSMEResponse
import com.bangkit.smeus.ui.api.SmeResponseList
import com.example.storyapp.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmeRepository {
    fun getSMEs(): List<DetailSMEResponse> {
        var data: List<DetailSMEResponse> = listOf()

        val client = ApiConfig.getApiService().fetchSMEs()
//        client.enqueue(object: Callback<SmeResponseList> {
//            override fun onResponse(
//                call: Call<SmeResponseList>,
//                response: Response<SmeResponseList>
//            ) {
//
//            }
//
//            override fun onFailure(call: Call<SmeResponseList>, t: Throwable) {
//
//            }
//        })

        return data
    }
}