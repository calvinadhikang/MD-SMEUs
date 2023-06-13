package com.bangkit.smeus.ui.api

import com.bangkit.smeus.ui.api.response.SMEResponse
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

    @GET("sme")
    fun fetchSMEs(): Call<SMEResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<RegisterResponse>
}