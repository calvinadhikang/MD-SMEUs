package com.bangkit.smeus.ui.api.response

import com.google.gson.annotations.SerializedName

data class SimilarityResponse(

	@field:SerializedName("result_fin")
	val resultFin: List<ResultFinItem?>? = null
)

data class ResultFinItem(

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
	val rating: Any,

	@field:SerializedName("price_range")
	val priceRange: String,

	@field:SerializedName("goods")
	val goods: String,

	@field:SerializedName("description")
	val description: String
)
