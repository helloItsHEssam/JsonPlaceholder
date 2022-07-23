package com.iamhessam.jsonplaceholder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.iamhessam.jsonplaceholder.ui.navigation.graph.NavGraph
import com.iamhessam.jsonplaceholder.ui.theme.JsonPlaceholderTheme
import com.iamhessam.jsonplaceholder.utils.settings.theme.ActiveColor
import com.iamhessam.jsonplaceholder.utils.settings.theme.Shape
import com.iamhessam.jsonplaceholder.utils.settings.theme.Typography
import com.iamhessam.jsonplaceholder.utils.settings.theme.UserSettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userSettings: UserSettings

//    private val btnChannel = Channel<HomeIntent>()
//    private val btnCancelChannel = Channel<HomeIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launchWhenStarted {
//            btnChannel.consumeAsFlow().collect(model::processorIntent)
//            btnCancelChannel.consumeAsFlow().collect(model::cancelIntent)
//        }
//
//        lifecycleScope.launchWhenStarted {
//            model.states().collect(::render)
//        }

        setContent {
            val viewState = userSettings.activeColor.collectAsState(initial = ActiveColor.System)
            JsonPlaceholderTheme(
                activeColor = viewState.value,
                shape = Shape(),
                typography = Typography()
            ) {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}