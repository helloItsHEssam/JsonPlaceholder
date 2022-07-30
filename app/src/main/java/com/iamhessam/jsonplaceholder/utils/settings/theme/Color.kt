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

enum class ThemeColor {
    DARK,
    LIGHT
}

sealed class ActiveColor {
    object System : ActiveColor()
    data class User(var themeColor: ThemeColor) : ActiveColor()

    val convertToString: String
        get() = when (this) {
            is System -> "System"
            is User -> if (themeColor == ThemeColor.DARK) "Dark" else "Light"
        }

    companion object {
        fun createFromString(activeColorString: String): ActiveColor {
            return when (activeColorString) {
                "Dark" -> User(ThemeColor.DARK)
                "Light" -> User(ThemeColor.LIGHT)
                else -> System
            }
        }
    }
}