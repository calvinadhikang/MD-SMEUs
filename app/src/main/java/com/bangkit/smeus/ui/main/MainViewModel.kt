package com.bangkit.smeus.ui.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bangkit.smeus.ui.api.LoginResponse
import com.bangkit.smeus.ui.api.UserPreferenceResponse
import com.bangkit.smeus.ui.repository.SmeRepository
import com.bangkit.smeus.ui.user.UserActivity
import com.example.storyapp.api.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val repository: SmeRepository
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    private var _userLogin = MutableStateFlow<UserPreferenceResponse>(UserPreferenceResponse())
    val userLogin: StateFlow<UserPreferenceResponse> get() = _userLogin

    fun login(email: String, password: String, context: Context){
        _loading.value = true

        val client = ApiConfig.getApiService().fetchUserPreference(email)
        client.enqueue(object: Callback<UserPreferenceResponse> {
            override fun onResponse(
                call: Call<UserPreferenceResponse>,
                response: Response<UserPreferenceResponse>
            ) {
                if (response.isSuccessful){
                    val user: UserPreferenceResponse = response.body()!!

                    if (user.email == email && user.password == password){
                        //set user preference
                        context.startActivity(Intent(context, UserActivity::class.java))
                    }else{
                        Toast.makeText(context, "Login Unsuccessful", Toast.LENGTH_SHORT).show()
                    }
                }
                _loading.value = false
            }

            override fun onFailure(call: Call<UserPreferenceResponse>, t: Throwable) {
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