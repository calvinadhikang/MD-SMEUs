package com.bangkit.smeus.ui.navigation

sealed class Screen(val route: String){
    object Explore : Screen("home")
    object Search : Screen("search/{key}"){
        fun createRoute(key: String) = "search/$key"
    }
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("detail/{smeId}"){
        fun createRoute(smeId: String) = "detail/$smeId"
    }
}
