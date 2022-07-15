package com.iamhessam.jsonplaceholder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.iamhessam.jsonplaceholder.ui.navigation.graph.NavGraph
import com.iamhessam.jsonplaceholder.ui.theme.JsonPlaceholderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
            JsonPlaceholderTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}