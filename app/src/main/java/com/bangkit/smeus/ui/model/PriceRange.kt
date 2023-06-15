package com.bangkit.smeus.ui.model

class PriceRange(
    var id: Int,
    var text: String
) {
    companion object{
        val listPriceRange: List<PriceRange> = listOf(
            PriceRange(0, "25.000-50.000"),
            PriceRange(0, "<25.000"),
            PriceRange(0, ">50.000"),
        )
    }
}