package com.bangkit.smeus.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.api.DetailSMEResponse
import com.bangkit.smeus.ui.api.RegisterResponse
import com.bangkit.smeus.ui.api.SmeResponseList
import com.example.storyapp.api.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _smeList = MutableStateFlow<SnapshotStateList<DetailSMEResponse>>(mutableStateListOf())
    val smeList: StateFlow<SnapshotStateList<DetailSMEResponse>> get() = _smeList

    fun fetchSME() {
        val client = ApiConfig.getApiService().fetchSMEs()
        client.enqueue(object : Callback<List<DetailSMEResponse>> {
            override fun onResponse(
                call: Call<List<DetailSMEResponse>>,
                response: Response<List<DetailSMEResponse>>
            ) {
                try {
                    if (response.isSuccessful) {
                        var resultList = response.body()!!

                        var mutableList = mutableStateListOf<DetailSMEResponse>()
                        resultList.forEachIndexed { index, detailSMEResponse ->
                            mutableList.add(detailSMEResponse)
                        }
                        _smeList.value = mutableList
                    }
                } catch (e: Exception) {
                    Log.e("ERROR_FETCH_SME_LIST", e.message.toString())
                }
            }

            override fun onFailure(call: Call<List<DetailSMEResponse>>, t: Throwable) {
            }

        })
    }
}