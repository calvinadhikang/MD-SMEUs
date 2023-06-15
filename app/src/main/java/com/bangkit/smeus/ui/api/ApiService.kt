package com.bangkit.smeus.ui.api

import com.bangkit.smeus.ui.api.response.SimilarityResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

//    @POST("register")
//    fun register(
//        @Body registerRequest: RegisterRequest
//    ): Call<RegisterResponse>
//
//    @POST("login")
//    fun login(
//        @Body loginRequest: LoginRequest
//    ): Call<LoginResponse>

    @GET("user/{user_email}")
    fun fetchUserPreference(
        @Path(value = "user_email", encoded = true) user_email: String
    ): Call<UserPreferenceResponse>

    @GET("similarity")
    fun fetchSimilarSME(
        @Query(value = "sme", encoded = false) smeName: String,
        @Query(value = "limit", encoded = true) limit: Int = 4
    ): Call<SimilarityResponse>

    @GET("sme/{sme_id}")
    fun fetchSMEbyId(
        @Path(value = "sme_id", encoded = true) smeId: String
    ): Call<DetailSMEResponse>

    @GET("sme")
    fun fetchSMEs(): Call<List<DetailSMEResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}