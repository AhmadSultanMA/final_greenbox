package com.irza.greenbox.feature.main.route

sealed class Screen(val route: String){
    object Splash : Screen("splash")
    object OnBoard : Screen("onBoard")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Dashboard : Screen("dashboard")
    object Feeds : Screen("feeds")
    object Leaderboard : Screen("leaderboard")
    object Reward : Screen("reward")
    object Scan : Screen("scan")
    object PostDetail : Screen("postdetail")
    object Redeem : Screen("redeem")
    object Profile : Screen("profile")

}