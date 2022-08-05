package com.iamhessam.jsonplaceholder.utils.settings.theme

import kotlinx.coroutines.flow.MutableStateFlow

interface UISettings {
    val shape: Shape
    val typography: Typography
    val activeColor: MutableStateFlow<ActiveColor>

    fun updateTheme(activeColor: ActiveColor)
}