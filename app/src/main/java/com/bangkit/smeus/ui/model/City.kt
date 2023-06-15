package com.bangkit.smeus.ui.model

class City(
    var id: Int,
    var text: String
) {
    companion object{
        val listCity: List<City> = listOf(
            City(0, "Bali"),
            City(0, "Bandung"),
            City(0, "Jakarta"),
            City(0, "Surakarta"),
            City(0, "Jogjakarta"),
        )
    }
}