package com.irza.greenbox.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.irza.greenbox.feature.dashboard.Dashboard
import com.irza.greenbox.feature.login.LoginScreen
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.feature.onBoard.OnBoard
import com.irza.greenbox.feature.signUp.SignUpScreen
import com.irza.greenbox.feature.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.OnBoard.route) {
            OnBoard(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController)
        }

        composable(route = Screen.Dashboard.route) {
            Dashboard(navController)
        }
    }
}
