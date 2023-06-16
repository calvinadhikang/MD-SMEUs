package com.bangkit.smeus.ui.model

class City(
    var id: Int,
    var text: String
) {
    companion object{
        val listCity: List<City> = listOf(
            City(0, "Bali"),
            City(1, "Bandung"),
            City(2, "Jakarta"),
            City(3, "Surakarta"),
            City(4, "Jogjakarta"),
        )

        fun getId(id:Int): String {
            var target:City = listCity[0]
            listCity.forEachIndexed { index, city ->
                if (city.id == id){
                    target = city
                }
            }

            return target.text
        }

        fun getIdObject(id:Int): City {
            var target:City = listCity[0]
            listCity.forEachIndexed { index, city ->
                if (city.id == id){
                    target = city
                }
            }

            return target
        }
    }
}