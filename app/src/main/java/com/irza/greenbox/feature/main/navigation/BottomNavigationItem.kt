package com.irza.greenbox.feature.main.navigation

import com.irza.greenbox.R
import com.irza.greenbox.feature.main.route.Screen

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val label: String
) {
    object Dashboard: BottomNavigationItem(route = Screen.Dashboard.route, icon = R.drawable.ic_dashboard, label = "Dashboard")
    object Feeds: BottomNavigationItem(route = Screen.Feeds.route, icon = R.drawable.ic_feeds, label = "Feeds")
    object Leaderboard: BottomNavigationItem(route = Screen.Leaderboard.route, icon = R.drawable.ic_leaderboard, label = "Leaderboard")
    object Reward: BottomNavigationItem(route = Screen.Reward.route, icon = R.drawable.ic_reward, label = "Reward")
}