package com.bangkit.smeus.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.api.DetailSMEResponse
import com.bangkit.smeus.ui.api.response.ResultFinItem
import com.bangkit.smeus.ui.api.response.SimilarityResponse
import com.bangkit.smeus.ui.navigation.Screen
import com.example.storyapp.api.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _smeList = MutableStateFlow<SnapshotStateList<DetailSMEResponse>>(mutableStateListOf())
    val smeList: StateFlow<SnapshotStateList<DetailSMEResponse>> get() = _smeList

    private var rawList: List<DetailSMEResponse> = listOf()

    fun fetchSME(email: String) {
        if (init == 0) {
            val client = ApiConfig.getApiService().fetchSMEs()
            client.enqueue(object : Callback<List<DetailSMEResponse>> {
                override fun onResponse(
                    call: Call<List<DetailSMEResponse>>,
                    response: Response<List<DetailSMEResponse>>
                ) {
                    if (response.isSuccessful) {
                        var resultList: List<DetailSMEResponse> = response.body()!!
                        rawList = resultList
                        filterSME()
                        FavoriteViewModel.init++
                    }
                }

                override fun onFailure(call: Call<List<DetailSMEResponse>>, t: Throwable) {
                }

            })
        }
    }

    fun filterSME(key:String = ""){
        var mutableList = mutableStateListOf<DetailSMEResponse>()
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