package com.iamhessam.jsonplaceholder.utils.extension

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.iamhessam.jsonplaceholder.utils.settings.theme.AppColor
import com.iamhessam.jsonplaceholder.utils.settings.theme.Shape
import com.iamhessam.jsonplaceholder.utils.settings.theme.Typography as AppTypo

val LocalColoring = compositionLocalOf { AppColor() }

val MaterialTheme.appColors: AppColor
    @Composable
    @ReadOnlyComposable
    get() = LocalColoring.current

val LocalShaping = staticCompositionLocalOf { Shape() }

val MaterialTheme.appShapes: Shape
    @Composable
    @ReadOnlyComposable
    get() = LocalShaping.current

val LocalTypography = staticCompositionLocalOf { AppTypo() }

val MaterialTheme.appTypography: AppTypo
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current