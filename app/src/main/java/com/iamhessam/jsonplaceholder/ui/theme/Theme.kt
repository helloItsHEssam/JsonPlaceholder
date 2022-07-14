package com.iamhessam.jsonplaceholder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun JsonPlaceholderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    // trigger
    val appColo = if (darkTheme) {
        DarkColor()
    } else {
        AppColor()
    }

    CompositionLocalProvider(LocalColoring provides appColo) {
        MaterialTheme(
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}