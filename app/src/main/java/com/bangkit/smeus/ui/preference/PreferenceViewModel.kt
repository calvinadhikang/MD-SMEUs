package com.bangkit.smeus.ui.preference

import androidx.lifecycle.ViewModel
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.model.City
import com.bangkit.smeus.ui.model.PriceRange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PreferenceViewModel: ViewModel() {

    private var _location = MutableStateFlow(City.listCity[0])
    val location: StateFlow<City> get() = _location

    private var _category = MutableStateFlow(Category.listCategory[0])
    val category: StateFlow<Category> get() = _category

    private var _priceRange = MutableStateFlow(PriceRange.listPriceRange[0])
    val priceRange: StateFlow<PriceRange> get() = _priceRange

    private var _rating = MutableStateFlow(1)
    val rating: StateFlow<Int> get() = _rating

    fun changeLocation(city: City){
        _location.value = city
    }

    fun changeCategory(category: Category){
        _category.value = category
    }

    fun changePriceRange(priceRange: PriceRange){
        _priceRange.value = priceRange
    }

    fun changeRating(rating: Int){
        _rating.value = rating
    }

    fun updatePreference(){

    }
}