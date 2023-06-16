package com.bangkit.smeus.ui.screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.UserPreference
import com.bangkit.smeus.ui.api.DetailSMEResponse
import com.bangkit.smeus.ui.api.response.ResultFinItem
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.model.City
import com.bangkit.smeus.ui.model.PriceRange
import com.example.storyapp.api.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel : ViewModel() {

    private val _smeList = MutableStateFlow<SnapshotStateList<DetailSMEResponse>>(mutableStateListOf())
    val smeList: StateFlow<SnapshotStateList<DetailSMEResponse>> get() = _smeList

    private var rawList: List<DetailSMEResponse> = listOf()

    fun fetchSME(context: Context) {
        val user = UserPreference(context).getUser()
        var city: String = City.getId(user.city)
        var category: String = Category.getId(user.generalCategory)

        val client = ApiConfig.getApiService().fetchSMEs(
            city = city,
            category = category,
        )
        client.enqueue(object : Callback<List<DetailSMEResponse>> {
            override fun onResponse(
                call: Call<List<DetailSMEResponse>>,
                response: Response<List<DetailSMEResponse>>
            ) {
                if (response.isSuccessful) {
                    var resultList = response.body()!!
                    rawList = resultList

                    filterSME()
                }
            }

            override fun onFailure(call: Call<List<DetailSMEResponse>>, t: Throwable) {
            }

        })
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
}