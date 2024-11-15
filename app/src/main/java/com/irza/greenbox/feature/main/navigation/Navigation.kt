package com.irza.greenbox.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.irza.greenbox.feature.home.Home
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.feature.onBoard.OnBoarding
import com.irza.greenbox.feature.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }

        composable(route = Screen.OnBoard.route) {
            OnBoarding(navController = navController)
        }
    }
}