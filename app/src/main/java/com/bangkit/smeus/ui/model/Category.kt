package com.bangkit.smeus.ui.model

import com.bangkit.smeus.R

class Category(
    var id: Int,
    var text: String,
    var image: Int = R.drawable.craft
) {
    companion object{
        val listCategory: List<Category> = listOf(
            Category(0, "Craft", R.drawable.craft),
            Category(1, "F&B", R.drawable.fnb),
            Category(2, "Fashion", R.drawable.fashion),
            Category(3, "Munchies", R.drawable.craft),
        )

        fun getId(id: Int): String{
            var target: Category = listCategory[0]
            listCategory.forEachIndexed { index, category ->
                if (category.id  == id){
                    target = category
                }
            }

            return target.text
        }
    }
}