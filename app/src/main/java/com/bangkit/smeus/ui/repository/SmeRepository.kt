package com.bangkit.smeus.ui.repository

import com.bangkit.smeus.ui.api.DetailSMEResponse
import com.example.storyapp.api.ApiConfig

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