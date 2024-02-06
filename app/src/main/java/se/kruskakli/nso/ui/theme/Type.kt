package se.kruskakli.nso.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import se.kruskakli.nso.R


object OpenSans{
    val Regular = FontFamily(Font(resId = R.font.opensans_regular))
    val Light = FontFamily(Font(resId = R.font.opensans_light))
    val Bold = FontFamily(Font(resId = R.font.opensans_bold))
    val Medium = FontFamily(Font(resId = R.font.opensans_medium))
    val Italic = FontFamily(Font(resId = R.font.opensans_italic))
}



// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Bold,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Italic,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Regular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Medium,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Medium,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),
    titleSmall = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Medium,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Regular,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Light,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        //fontFamily = FontFamily.Default,
        fontFamily = OpenSans.Light,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
