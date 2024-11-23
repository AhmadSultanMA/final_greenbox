package com.irza.greenbox.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustGreen
import com.irza.greenbox.ui.theme.Purple80
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    val fullText = "GreenBox"

    LaunchedEffect(Unit) {
        fullText.forEachIndexed { index, char ->
            text += char
            delay(200)
        }
        delay(1000)
        navController.navigate(Screen.OnBoard.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.displayMedium,
                color = CustGreen
            )
        }
    }
}