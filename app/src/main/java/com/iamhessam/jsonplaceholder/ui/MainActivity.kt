package com.iamhessam.jsonplaceholder.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.iamhessam.jsonplaceholder.ui.navigation.graph.NavGraph
import com.iamhessam.jsonplaceholder.ui.theme.JsonPlaceholderTheme
import com.iamhessam.jsonplaceholder.utils.settings.theme.ActiveColor
import com.iamhessam.jsonplaceholder.utils.settings.theme.Shape
import com.iamhessam.jsonplaceholder.utils.settings.theme.Typography
import com.iamhessam.jsonplaceholder.utils.settings.theme.UISettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userSettings: UISettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val activeColor = userSettings.activeColor.collectAsState(initial = ActiveColor.System)
            Log.d("HEssam THEME Activity", activeColor.value.toString())

            JsonPlaceholderTheme(
                activeColor = activeColor.value,
                shape = Shape(),
                typography = Typography()
            ) {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}