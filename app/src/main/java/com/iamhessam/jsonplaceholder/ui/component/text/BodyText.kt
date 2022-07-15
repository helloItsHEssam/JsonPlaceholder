package com.iamhessam.jsonplaceholder.ui.component.text

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iamhessam.jsonplaceholder.ui.theme.appColors
import com.iamhessam.jsonplaceholder.utils.constant.CallBack

@Composable
fun BodyText(text: String, clickable: CallBack) {
    Text(
        modifier = Modifier.clickable {
            clickable()
        },
        text = text,
        color = MaterialTheme.appColors.titleColor
    )
}