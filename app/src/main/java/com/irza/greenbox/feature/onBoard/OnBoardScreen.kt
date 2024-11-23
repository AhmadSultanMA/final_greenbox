package com.irza.greenbox.feature.onBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irza.greenbox.R
import com.irza.greenbox.feature.main.components.buttons.PrimaryButton
import com.irza.greenbox.feature.main.route.Screen
import com.irza.greenbox.ui.theme.CustBlack
import com.irza.greenbox.ui.theme.CustGreen
import kotlinx.coroutines.launch

@Composable
fun OnBoard(navController: NavController) {
    val onboardingPages = listOf(
        R.drawable.onboard1,
        R.drawable.onboard2,
        R.drawable.onboard3
    )

    val onboardingTexts = listOf(
        "Dengan GreenBox, Anda adalah bagian dari perubahan positif",
        "Dengan GreenBox untuk masa depan yang lebih baik dan nyaman",
        "Mulailah perubahan ke arah yang lebih baik dengan tangan anda"
    )

    val currentPage = remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(170.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = onboardingPages[currentPage.value]),
                contentDescription = "Onboarding Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.8f)
            )
        }

        Text(
            text = buildAnnotatedString {
                onboardingTexts[currentPage.value].split(" ").forEach { word ->
                    if (word == "GreenBox," || word == "GreenBox") {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = CustGreen)) {
                            append(" $word")
                        }
                    } else {
                        append(" $word")
                    }
                }
            },
            style = MaterialTheme.typography.headlineSmall,
            color = CustBlack,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.height(70.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(onboardingPages.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(
                            color = if (index == currentPage.value) CustGreen else Color.LightGray
                        )
                )

            }
        }
        Spacer(modifier = Modifier.height(40.dp))

        PrimaryButton(
            text = if (currentPage.value == onboardingPages.size - 1) "Get Started" else "Lanjutkan",
            onClick = {
                coroutineScope.launch {
                    if (currentPage.value < onboardingPages.size - 1) {
                        currentPage.value += 1
                    } else {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.OnBoard.route) { inclusive = true }
                        }
                    }
                }
            }
        )

    }
}


