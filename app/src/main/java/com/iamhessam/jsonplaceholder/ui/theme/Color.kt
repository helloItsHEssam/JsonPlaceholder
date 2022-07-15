package com.iamhessam.jsonplaceholder.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

open class AppColor(
    open var backgroundColor: Color = Color.White,
    open var titleColor: Color = Color.Black
)

class DarkColor : AppColor() {
    override var backgroundColor: Color = Color.Black
    override var titleColor: Color = Color.White
}

val LocalColoring = compositionLocalOf { AppColor() }

val MaterialTheme.appColors: AppColor
    @Composable
    @ReadOnlyComposable
    get() = LocalColoring.current