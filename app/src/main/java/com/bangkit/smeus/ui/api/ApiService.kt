package com.bangkit.smeus.ui.api

import com.bangkit.smeus.ui.api.response.SimilarityResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("user/{user_email}/wishlist")
    fun fetchWishlistUser(
        @Path(value = "user_email", encoded = true) user_email: String,
    ): Call<SimilarityResponse>

    @FormUrlEncoded
    @PUT("wishlist")
    fun updateWishlist(
        @Field("email") email:String,
        @Field("index") smeId:String,
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @PUT("user/{user_email}")
    fun updateUserPreference(
        @Path(value = "user_email", encoded = true) user_email: String,
        @Field("city") city: Int,
        @Field("generalCategory") generalCategory: Int,
        @Field("priceRange") priceRange: Int,
        @Field("rating") rating: Int
    ): Call<RegisterResponse>

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
    fun fetchSMEs(
        @Query(value = "city") city: String = "",
        @Query(value = "general_category", encoded = false) category: String = "",
        @Query(value = "price_range") price_range: String = "",
    ): Call<List<DetailSMEResponse>>

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