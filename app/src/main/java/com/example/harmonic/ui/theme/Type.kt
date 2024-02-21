package com.example.harmonic.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.harmonic.R

val VarelaRoundFontFamily = FontFamily(
        listOf(
                Font(R.font.varela_round)
        )
)

val Typography = Typography(
        // Display Large - VarelaRound 57/64 . -0.25px
        displayLarge = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 57.sp,
                lineHeight = 64.sp,
                letterSpacing = (-0.25).sp,
        ),

        // Display Medium - VarelaRound 45/52 . 0px
        displayMedium = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 45.sp,
                lineHeight = 52.sp,
                letterSpacing = 0.sp,
        ),

        // Display Small - VarelaRound 36/44 . 0px
        displaySmall = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                letterSpacing = 0.sp,
        ),

        // Headline Large - VarelaRound 32/40 . 0px
        headlineLarge = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                letterSpacing = 0.sp,
        ),

        // Headline Medium - VarelaRound 28/36 . 0px
        headlineMedium = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp,
        ),

        // Headline Small - VarelaRound 24/32 . 0px
        headlineSmall = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp,
        ),

        // Title Large - VarelaRound 22/28 . 0px
        titleLarge = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
        ),

        // Title Medium - VarelaRound 16/24 . 0.15px
        titleMedium = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
        ),

        // Title Small - VarelaRound 14/20 . 0.1px
        titleSmall = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
        ),

        // Label Large - VarelaRound 14/20 . 0.1px
        labelLarge = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
        ),

        // Label Medium - VarelaRound 12/16 . 0.5px
        labelMedium = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
        ),

        // Label Small - VarelaRound 11/16 . 0.5px
        labelSmall = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
        ),

        // Body Large - VarelaRound 16/24 . 0.5px
        bodyLarge = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
        ),

        // Body Medium - VarelaRound 14/20 . 0.25px
        bodyMedium = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
        ),
        // Body Small - VarelaRound 12/16 . 0.4px
        bodySmall = TextStyle(
                fontFamily = VarelaRoundFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
        ),
)