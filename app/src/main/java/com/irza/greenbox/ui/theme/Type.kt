package com.irza.greenbox.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.irza.greenbox.R

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 43.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 43.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 27.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 19.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
)