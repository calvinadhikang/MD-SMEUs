package com.bangkit.smeus.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.UserPreference
import com.bangkit.smeus.ui.api.LoginResponse
import com.bangkit.smeus.ui.api.UserPreferenceResponse
import com.bangkit.smeus.ui.preference.PreferenceActivity
import com.bangkit.smeus.ui.user.UserActivity
import com.example.storyapp.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    fun login(email: String, password: String, context: Context){
        _loading.value = true

        val client = ApiConfig.getApiService().login(email, password)
        client.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 400){
                    Toast.makeText(context, "Login Unsuccessful", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                    userPreference(email, context)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            }
        })
    }

    fun userPreference(email: String, context: Context){
        val client = ApiConfig.getApiService().fetchUserPreference(email)
        client.enqueue(object: Callback<UserPreferenceResponse> {
            override fun onResponse(
                call: Call<UserPreferenceResponse>,
                response: Response<UserPreferenceResponse>
            ) {
                if (response.isSuccessful){
                    val user: UserPreferenceResponse = response.body()!!

                    //set User Preference
                    val preference = UserPreference(context)
                    preference.setUser(user)

                    val activity = context as Activity
                    if (user.city == -1){
                        activity.startActivity(Intent(context, PreferenceActivity::class.java))
                    }else{
                        activity.startActivity(Intent(context, UserActivity::class.java))
                    }
                }
                _loading.value = false
            }

            override fun onFailure(call: Call<UserPreferenceResponse>, t: Throwable) {
            }
        })
    }

    fun checkUserSaved(context: Context){
        val activity = context as Activity
        val preference = UserPreference(context)
        val user = preference.getUser()
        if (user.email != ""){
            Toast.makeText(context, "Welcome back, ${user.name}", Toast.LENGTH_SHORT).show()
            if (user.city == -1){
                activity.startActivity(Intent(context, PreferenceActivity::class.java))
            }else{
                activity.startActivity(Intent(context, UserActivity::class.java))
            }
        }
    }
}