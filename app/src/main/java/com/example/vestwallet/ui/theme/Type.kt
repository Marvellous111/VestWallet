package com.example.vestwallet.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.vestwallet.R

//Set up the fonts for the android app
val Poppins = FontFamily(
    Font(
        R.font.poppins_semibold,
        FontWeight.SemiBold,
    ),
    Font(
        R.font.poppins_bold,
        FontWeight.Bold
    ),
    Font(
        R.font.poppins_regular,
        FontWeight.Normal
    ),
    Font(
        R.font.poppins_medium,
        FontWeight.Medium
    ),
    Font(
        R.font.poppins_light,
        FontWeight.Light
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        letterSpacing = (-5).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 64.sp,
        shadow = Shadow(
            color = Color(0xFF121212),
            offset = Offset(2F, 3F),
            blurRadius = 15F
        )
    ),
    headlineLarge = TextStyle(
        letterSpacing = (-3).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    headlineSmall = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    ),
    titleLarge = TextStyle(
        letterSpacing = (-2).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp
    ),
    titleMedium = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp
    ),
    bodyLarge = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    ),
    bodySmall = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    ),
    labelMedium = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        letterSpacing = (-1).sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)