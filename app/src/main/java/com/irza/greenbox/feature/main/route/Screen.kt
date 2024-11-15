package com.irza.greenbox.feature.main.route

sealed class Screen(val route: String){
    object Splash : Screen("splash")
    object Home : Screen("home")
    object OnBoard : Screen("onBoard")

}