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