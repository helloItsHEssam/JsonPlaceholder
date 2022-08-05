package com.iamhessam.jsonplaceholder.ui.component.text

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iamhessam.jsonplaceholder.utils.constant.CallBack
import com.iamhessam.jsonplaceholder.utils.extension.appColors
import com.iamhessam.jsonplaceholder.utils.extension.appTypography

@Composable
fun TextBody(text: String, clickable: CallBack? = null) {
    Text(
        modifier = Modifier.clickable {
            clickable?.let {
                it()
            }
        },
        text = text,
        color = MaterialTheme.appColors.titleColor,
        style = MaterialTheme.appTypography.body
    )
}