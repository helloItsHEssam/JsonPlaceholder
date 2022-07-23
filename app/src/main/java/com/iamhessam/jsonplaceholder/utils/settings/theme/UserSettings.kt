package com.iamhessam.jsonplaceholder.utils.settings.theme

import kotlinx.coroutines.flow.Flow

interface UserSettings {
    val shape: Shape
    val typography: Typography
    val activeColor: Flow<ActiveColor>
}