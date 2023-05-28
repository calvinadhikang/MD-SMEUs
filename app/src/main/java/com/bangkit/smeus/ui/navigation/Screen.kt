package com.bangkit.smeus.ui.navigation

sealed class Screen(val route: String){
    object Home : Screen("home")
    object Search : Screen("search")
    object Favorite : Screen("search")
    object Profile : Screen("profile")
}
