package com.bangkit.smeus.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.api.response.ResultFinItem
import com.bangkit.smeus.ui.api.response.SimilarityResponse
import com.example.storyapp.api.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _smeList = MutableStateFlow<SnapshotStateList<ResultFinItem>>(mutableStateListOf())
    val smeList: StateFlow<SnapshotStateList<ResultFinItem>> get() = _smeList

    fun fetchSME(email: String, key:String = "") {
        val client = ApiConfig.getApiService().fetchWishlistUser(email)
        client.enqueue(object : Callback<SimilarityResponse> {
            override fun onResponse(
                call: Call<SimilarityResponse>,
                response: Response<SimilarityResponse>
            ) {
                if (response.isSuccessful) {
                    var resultList = response.body()!!.resultFin!!

                    var mutableList = mutableStateListOf<ResultFinItem>()
                    resultList.forEachIndexed { index, detailSMEResponse ->
                        if (key != ""){
                            if (key.contains(detailSMEResponse!!.nameSmes, ignoreCase = true)){
                                mutableList.add(detailSMEResponse)
                            }
                        }else{
                            mutableList.add(detailSMEResponse!!)
                        }
                    }
                    _smeList.value = mutableList
                }
            }

            override fun onFailure(call: Call<SimilarityResponse>, t: Throwable) {
            }
        })
    }
}