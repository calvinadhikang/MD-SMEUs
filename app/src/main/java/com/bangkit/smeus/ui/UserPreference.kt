package com.bangkit.smeus.ui

import android.content.Context
import com.bangkit.smeus.ui.api.RegisterResponse
import com.bangkit.smeus.ui.api.UserPreferenceResponse
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.model.City
import com.bangkit.smeus.ui.model.PriceRange
import com.example.storyapp.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class UserPreference(context: Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(value: UserPreferenceResponse) {
        val editor = preferences.edit()
        editor.putString(NAME, value.name)
        editor.putString(EMAIL, value.email)
        editor.putString(PASSWORD, value.password)
        editor.putString(PHONE, value.phone)
        editor.putInt(CITY, value.city)
        editor.putInt(GENERAL_CATEGORY, value.generalCategory)
        editor.putInt(PRICE_RANGE, value.priceRange)
        editor.putInt(RATING, value.rating)
        editor.apply()
    }

    fun getUser(): UserPreferenceResponse {
        val name = preferences.getString(NAME, "")
        val email = preferences.getString(EMAIL, "")
        val password = preferences.getString(PASSWORD, "")
        val phone = preferences.getString(PHONE, "")
        val city = preferences.getInt(CITY, -1)
        val generalCategory = preferences.getInt(GENERAL_CATEGORY, -1)
        val priceRange = preferences.getInt(PRICE_RANGE, -1)
        val rating = preferences.getInt(RATING, -1)

        return UserPreferenceResponse(password!!, generalCategory, phone!!, city, name!!, rating, priceRange, email!!)
    }

    fun updateUserPreference(generalCategory: Category, city: City, rating: Int, priceRange: PriceRange){
        val userOld = this.getUser()
        userOld.city = city.id
        userOld.generalCategory = generalCategory.id
        userOld.rating = rating
        userOld.priceRange = priceRange.id

        val client = ApiConfig.getApiService().updateUserPreference(
            user_email = userOld.email,
            generalCategory = generalCategory.id,
            city = city.id,
            priceRange = priceRange.id,
            rating = rating
        )
        client.enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    val resp = response.body()!!
                    if (resp.message == "User preferences updated successfully"){
                        setUser(userOld)
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            }
        })
    }

    fun clearUser(){
        preferences.edit().clear().commit()
    }

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val PHONE = "phone"
        private const val CITY = "city"
        private const val GENERAL_CATEGORY = "general_category"
        private const val PRICE_RANGE = "price_range"
        private const val RATING = "rating"
    }
}