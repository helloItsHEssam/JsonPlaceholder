package com.iamhessam.jsonplaceholder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.iamhessam.jsonplaceholder.utils.extension.LocalColoring
import com.iamhessam.jsonplaceholder.utils.extension.LocalShaping
import com.iamhessam.jsonplaceholder.utils.extension.LocalTypography
import com.iamhessam.jsonplaceholder.utils.settings.theme.AppColor
import com.iamhessam.jsonplaceholder.utils.settings.theme.DarkColor
import com.iamhessam.jsonplaceholder.utils.settings.theme.Shape
import com.iamhessam.jsonplaceholder.utils.settings.theme.Typography

@Composable
fun JsonPlaceholderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val appColo = if (darkTheme) {
        DarkColor()
    } else {
        AppColor()
    }

    CompositionLocalProvider(
        LocalColoring provides appColo,
        LocalShaping provides Shape(),
        LocalTypography provides Typography()
    ) {
        MaterialTheme(content = content)
    }
}