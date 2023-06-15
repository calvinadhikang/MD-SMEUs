package com.bangkit.smeus.ui.model

class Category(
    var id: Int,
    var text: String,
    var selected: Boolean = false
) {
    companion object{
        val listCategory: List<Category> = listOf(
            Category(0, "Craft"),
            Category(1, "F&B"),
            Category(2, "Fashion"),
            Category(3, "Munchies"),
        )
    }
}