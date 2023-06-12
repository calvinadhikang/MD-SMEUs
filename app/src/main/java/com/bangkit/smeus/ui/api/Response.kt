package com.bangkit.smeus.ui.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse(
    @field:SerializedName("message")
    val message: String
): Parcelable