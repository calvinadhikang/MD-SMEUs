package com.bangkit.smeus.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bangkit.smeus.ui.api.LoginResponse
import com.bangkit.smeus.ui.api.response.SMEResponse
import com.bangkit.smeus.ui.api.response.SMEResponseItem
import com.bangkit.smeus.ui.repository.SmeRepository
import com.example.storyapp.api.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val repository: SmeRepository
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    private val _listSmes = MutableLiveData<List<SMEResponseItem>>()
    var listSmes: LiveData<List<SMEResponseItem>> = _listSmes

    fun login(email: String, password: String){
        _loading.value = true

        val client = ApiConfig.getApiService().login(email, password)
        client.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            }
        })
    }

    fun getData() {
        val client = ApiConfig.getApiService().fetchSMEs()
        client.enqueue(object: Callback<SMEResponse> {
            override fun onResponse(call: Call<SMEResponse>, response: Response<SMEResponse>) {
                if (response.isSuccessful){
                    Log.e("TEST", response.body()!!.toString())
                    _listSmes.value = response.body()!!.sMEResponse as List<SMEResponseItem>
                }
            }

            override fun onFailure(call: Call<SMEResponse>, t: Throwable) {

            }
        })
    }
}

class ViewModelFactory(private val repository: SmeRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }
}