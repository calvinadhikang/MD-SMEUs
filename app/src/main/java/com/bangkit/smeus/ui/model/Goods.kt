package com.bangkit.smeus.ui.model

class Goods(
    var id: Int,
    var text: String
) {
    companion object{
        val listGoods: List<Goods> = listOf(
            Goods(0, "Casual Fashion"),
            Goods(1, "Daily Needs"),
            Goods(2, "Formal Fashion"),
            Goods(3, "Household Items"),
            Goods(4, "Main Course"),
            Goods(5, "Snack"),
            Goods(6, "Souvenir"),
            Goods(7, "Street Food"),
            Goods(8, "Street Snacks"),
        )

        fun randomGoods(): Goods {
            val rnd = (0..8).random()
            return listGoods[rnd]
        }
    }
}