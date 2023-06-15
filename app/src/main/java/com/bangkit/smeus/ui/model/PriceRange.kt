package com.bangkit.smeus.ui.model

class PriceRange(
    var id: Int,
    var text: String
) {
    companion object{
        val listPriceRange: List<PriceRange> = listOf(
            PriceRange(0, "25K - 50K"),
            PriceRange(0, "< 25K"),
            PriceRange(0, "> 50K"),
        )
    }
}