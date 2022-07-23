package com.iamhessam.jsonplaceholder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.iamhessam.jsonplaceholder.utils.extension.LocalColoring
import com.iamhessam.jsonplaceholder.utils.extension.LocalShaping
import com.iamhessam.jsonplaceholder.utils.extension.LocalTypography
import com.iamhessam.jsonplaceholder.utils.settings.theme.*

@Composable
fun JsonPlaceholderTheme(
    activeColor: ActiveColor,
    shape: Shape,
    typography: Typography,
    content: @Composable () -> Unit
) {

    val color = colorFromActiveColor(activeColor = activeColor)

    CompositionLocalProvider(
        LocalColoring provides color,
        LocalShaping provides shape,
        LocalTypography provides typography
    ) {
        MaterialTheme(content = content)
    }
}

@Composable
private fun colorFromActiveColor(activeColor: ActiveColor): AppColor {
    return when (activeColor) {
        is ActiveColor.System -> if (isSystemInDarkTheme()) AppColor() else DarkColor()
        is ActiveColor.User -> if (activeColor.isDark) DarkColor() else AppColor()
    }
}