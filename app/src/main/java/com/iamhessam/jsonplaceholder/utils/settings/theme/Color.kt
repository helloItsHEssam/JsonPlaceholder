package com.iamhessam.jsonplaceholder.utils.settings.theme

import androidx.compose.ui.graphics.Color

open class AppColor(
    open var backgroundColor: Color = Color.White,
    open var titleColor: Color = Color.Black
)

class DarkColor : AppColor() {
    override var backgroundColor: Color = Color.Black
    override var titleColor: Color = Color.White
}

sealed class ActiveColor {
    object System : ActiveColor()
    data class User(val isDark: Boolean) : ActiveColor()

    val convertToString: String
        get() = when (this) {
            is System -> "System"
            is User -> if (isDark) "Dark" else "Light"
        }

    companion object {
        fun createFromString(activeColorString: String): ActiveColor {
            return when (activeColorString) {
                "Dark" -> User(true)
                "Light" -> User(false)
                else -> System
            }
        }
    }
}