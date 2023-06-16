package com.bangkit.smeus.ui.model

class PriceRange(
    var id: Int,
    var text: String
) {
    companion object{
        val listPriceRange: List<PriceRange> = listOf(
            PriceRange(0, "25.000-50.000"),
            PriceRange(1, "<25.000"),
            PriceRange(2, ">50.000"),
        )

        fun getId(id: Int): String {
            var target: PriceRange = listPriceRange[0]
            listPriceRange.forEachIndexed { index, priceRange ->
                if (priceRange.id == id){
                    target = priceRange
                }
            }

            return target.text
        }

        fun getIdObject(id: Int): PriceRange {
            var target: PriceRange = listPriceRange[0]
            listPriceRange.forEachIndexed { index, priceRange ->
                if (priceRange.id == id){
                    target = priceRange
                }
            }

            return target
        }
    }
}