package com.bangkit.smeus.ui.api.response

import com.google.gson.annotations.SerializedName

data class SMEResponse(

	@field:SerializedName("SMEResponse")
	val sMEResponse: List<SMEResponseItem?>? = null
)

data class SMEResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("specialty")
	val specialty: String? = null,

	@field:SerializedName("general_category")
	val generalCategory: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name_smes")
	val nameSmes: String? = null,

	@field:SerializedName("contact")
	val contact: String? = null,

	@field:SerializedName("index_place")
	val indexPlace: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("price_range")
	val priceRange: String? = null,

	@field:SerializedName("goods")
	val goods: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)
