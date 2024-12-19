package com.irza.greenbox.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.irza.greenbox.camera.CameraScreen
import com.irza.greenbox.feature.dashboard.Dashboard
import com.irza.greenbox.feature.feed.FeedScreen
import com.irza.greenbox.feature.leaderboard.LeaderBoardScreen
import com.irza.greenbox.feature.login.LoginScreen
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.feature.onBoard.OnBoard
import com.irza.greenbox.feature.postDetail.PostDetailScreen
import com.irza.greenbox.feature.profile.ProfileScreen
import com.irza.greenbox.feature.redeem.RedeemScreen
import com.irza.greenbox.feature.reward.RewardScreen
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

        composable(route = Screen.Scan.route) {
            CameraScreen(navController)
        }

        composable(route = Screen.Feeds.route) {
            FeedScreen(navController)
        }

        composable(route = Screen.Leaderboard.route) {
            LeaderBoardScreen(navController)
        }

        composable(route = "${Screen.PostDetail.route}/{post_id}",
            arguments = listOf(
                navArgument("post_id") {
                    type = NavType.StringType
                }
            ))
        {
            val post_id = it.arguments?.getString("post_id") ?: ""
            PostDetailScreen(navController = navController, post_id)
        }

        composable(route = Screen.Reward.route) {
            RewardScreen(navController)
        }

        composable(route = "${Screen.Redeem.route}/{redeem_id}",
            arguments = listOf(
                navArgument("redeem_id") {
                    type = NavType.StringType
                }
            ))
        {
            val redeem_id = it.arguments?.getString("redeem_id") ?: ""
            RedeemScreen(navController = navController, redeem_id)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}
