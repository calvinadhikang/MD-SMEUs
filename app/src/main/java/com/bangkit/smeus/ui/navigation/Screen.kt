package com.bangkit.smeus.ui.navigation

sealed class Screen(val route: String){
    object Explore : Screen("home")
    object Search : Screen("search")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
}
