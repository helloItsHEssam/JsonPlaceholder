package com.iamhessam.jsonplaceholder.utils.settings.theme

import kotlinx.coroutines.flow.StateFlow

interface UserSettings {

    val shape: Shape
    val typography: Typography

    val activeColor: StateFlow<ActiveColor>
}