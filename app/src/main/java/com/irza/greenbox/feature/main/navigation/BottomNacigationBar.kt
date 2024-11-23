package com.irza.greenbox.feature.main.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustDarkGrayField
import com.irza.greenbox.ui.theme.CustGrayField
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.CustWhite

@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar(
        containerColor = CustWhite,
        tonalElevation = 16.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // Ikon kiri pertama
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustGreen,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustWhite
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItem.Dashboard.icon),
                    contentDescription = BottomNavigationItem.Dashboard.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationItem.Dashboard.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sans_medium)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationItem.Dashboard.route) CustGreen else CustGrayField
                )
            },
            selected = currentRoute == BottomNavigationItem.Dashboard.route,
            onClick = {
                navController.navigate(BottomNavigationItem.Dashboard.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            },
        )


        // Ikon kedua
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustGreen,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustWhite
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItem.Feeds.icon),
                    contentDescription = BottomNavigationItem.Feeds.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationItem.Feeds.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sans_medium)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationItem.Feeds.route) CustGreen else Color.Gray
                )
            },
            selected = currentRoute == BottomNavigationItem.Feeds.route,
            onClick = {
                navController.navigate(BottomNavigationItem.Feeds.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            }
        )

        // Spacer untuk tombol tengah
        Spacer(modifier = Modifier.weight(1f))

        // Ikon ketiga
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustGreen,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustWhite
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItem.Leaderboard.icon),
                    contentDescription = BottomNavigationItem.Leaderboard.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationItem.Leaderboard.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sans_medium)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationItem.Leaderboard.route) CustGreen else Color.Gray
                )
            },
            selected = currentRoute == BottomNavigationItem.Leaderboard.route,
            onClick = {
                navController.navigate(BottomNavigationItem.Leaderboard.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            }
        )

        // Ikon keempat
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustGreen,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustWhite
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItem.Reward.icon),
                    contentDescription = BottomNavigationItem.Reward.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationItem.Reward.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sans_medium)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationItem.Reward.route) CustGreen else Color.Gray
                )
            },
            selected = currentRoute == BottomNavigationItem.Reward.route,
            onClick = {
                navController.navigate(BottomNavigationItem.Reward.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.Scan.route)
            },
            shape = CircleShape,
            containerColor = CustGreen
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_scan),
                contentDescription = "Scan Now",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        // Teks di atas FAB
        Text(
            text = "Scan Now",
            color = CustGreen,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = -20.dp)
        )
    }
}
