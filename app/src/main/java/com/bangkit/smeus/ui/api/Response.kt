package com.bangkit.smeus.ui.api

import android.os.Parcelable
import com.bangkit.smeus.ui.api.response.ResultFinItem
import com.bangkit.smeus.ui.navigation.Screen
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RegisterResponse(
    @field:SerializedName("message")
    val message: String
)

data class LoginResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("token")
    val token: String,
)

data class SmeResponseList(

    @field:SerializedName("SmeResponse")
    val smeResponseList: List<DetailSMEResponse?>? = null
)

data class SimilarSmeResponseList(

    @field:SerializedName("result_fin")
    val resultFin: List<DetailSMEResponse>
)

data class UserPreferenceResponse(

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("generalCategory")
    val generalCategory: Int? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("city")
    val city: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("rating")
    val rating: Int? = null,

    @field:SerializedName("priceRange")
    val priceRange: Int? = null,

    @field:SerializedName("email")
    val email: String? = null
)

data class DetailSMEResponse(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("specialty")
    val specialty: String,

    @field:SerializedName("general_category")
    val generalCategory: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("name_smes")
    val nameSmes: String,

    @field:SerializedName("contact")
    val contact: String,

    @field:SerializedName("index_place")
    val indexPlace: String,

    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("price_range")
    val priceRange: String,

    @field:SerializedName("goods")
    val goods: String,

    @field:SerializedName("description")
    val description: String
)