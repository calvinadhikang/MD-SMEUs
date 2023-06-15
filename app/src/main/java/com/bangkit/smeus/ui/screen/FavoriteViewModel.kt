package com.bangkit.smeus.ui.screen

import android.util.Log
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

class FavoriteViewModel : ViewModel() {

    private val _smeList = MutableStateFlow<SnapshotStateList<ResultFinItem>>(mutableStateListOf())
    val smeList: StateFlow<SnapshotStateList<ResultFinItem>> get() = _smeList

    private var rawList: List<ResultFinItem> = listOf()

    fun fetchSME(email: String) {
        if (init == 0) {
            val client = ApiConfig.getApiService().fetchWishlistUser(email)
            client.enqueue(object : Callback<SimilarityResponse> {
                override fun onResponse(
                    call: Call<SimilarityResponse>,
                    response: Response<SimilarityResponse>
                ) {
                    if (response.isSuccessful) {
                        var resultList = response.body()!!.resultFin!!
                        rawList = resultList as List<ResultFinItem>
                        filterSME()
                        init++
                    }
                }

                override fun onFailure(call: Call<SimilarityResponse>, t: Throwable) {
                }
            })
        }
    }

    fun filterSME(key:String = ""){
        var mutableList = mutableStateListOf<ResultFinItem>()
        rawList.forEachIndexed { index, detailSMEResponse ->
            if (key != ""){
                if (detailSMEResponse.nameSmes.contains(key, ignoreCase = true)){
                    mutableList.add(detailSMEResponse)
                }
            }else{
                mutableList.add(detailSMEResponse)
            }
        }
        Log.e("LIST_FILTER_COUNT", mutableList.size.toString())
        _smeList.value = mutableList
    }

    companion object{
        var init = 0
    }
}