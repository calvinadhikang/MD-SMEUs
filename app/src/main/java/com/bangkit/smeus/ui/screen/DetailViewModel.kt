package com.bangkit.smeus.ui.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.api.DetailSMEResponse
import com.bangkit.smeus.ui.api.RegisterResponse
import com.bangkit.smeus.ui.api.SimilarSmeResponseList
import com.bangkit.smeus.ui.api.SmeResponseList
import com.bangkit.smeus.ui.api.response.ResultFinItem
import com.bangkit.smeus.ui.api.response.SimilarityResponse
import com.bangkit.smeus.ui.navigation.Screen
import com.example.storyapp.api.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _sme = MutableStateFlow(DetailSMEResponse("", "","","","","","",0,"","","",))
    val sme: StateFlow<DetailSMEResponse> = _sme

    private val _similarSme = MutableStateFlow<SnapshotStateList<ResultFinItem>>(mutableStateListOf())
    val similarSme: StateFlow<SnapshotStateList<ResultFinItem>> get() = _similarSme

    fun fetchSME(id: String) {
        val client = ApiConfig.getApiService().fetchSMEbyId(id)
        client.enqueue(object : Callback<DetailSMEResponse> {
            override fun onResponse(
                call: Call<DetailSMEResponse>,
                response: Response<DetailSMEResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()!!

                    _sme.value = data
                    fetchSimilarSME(data.nameSmes)
                }
            }

            override fun onFailure(call: Call<DetailSMEResponse>, t: Throwable) {
            }
        })
    }

    fun fetchSimilarSME(name: String) {
        try {
            val client = ApiConfig.getApiService().fetchSimilarSME(name)
            client.enqueue(object : Callback<SimilarityResponse> {
                override fun onResponse(
                    call: Call<SimilarityResponse>,
                    response: Response<SimilarityResponse>
                ) {
                    if (response.isSuccessful){
                        val list = response.body()!!.resultFin!!

                        val mutableList = mutableStateListOf<ResultFinItem>()
                        list.forEachIndexed { index, resultFinItem ->  mutableList.add(resultFinItem!!) }
                        _similarSme.value = mutableList
                    }
                }

                override fun onFailure(call: Call<SimilarityResponse>, t: Throwable) {

                }

            })
        }
        catch (e: Exception){
            Log.e("SIMILAR_FETCH_ERROR", e.message.toString())
        }
    }
}