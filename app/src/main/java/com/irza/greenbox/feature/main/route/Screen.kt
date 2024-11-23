package com.irza.greenbox.feature.main.route

sealed class Screen(val route: String){
    object Splash : Screen("splash")
    object Home : Screen("home")
    object OnBoard : Screen("onBoard")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Dashboard : Screen("dashboard")

}