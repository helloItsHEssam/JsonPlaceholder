package com.iamhessam.jsonplaceholder.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.iamhessam.jsonplaceholder.ui.mvi.MviView
import com.iamhessam.jsonplaceholder.ui.navigation.graph.NavGraph
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.*
import com.iamhessam.jsonplaceholder.ui.theme.JsonPlaceholderTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow

class MainActivity : ComponentActivity(),
    MviView<HomeResult, HomeProcessor, HomeAction, HomeIntent, HomeViewState> {

    private val model = HomeModel()

    override fun render(state: HomeViewState) {
        Log.d("new Stateeeeeeee", state.toString())
    }

    private val btnChannel = Channel<HomeIntent>()
    private val btnCancelChannel = Channel<HomeIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            btnChannel.consumeAsFlow().collect(model::processorIntent)
            btnCancelChannel.consumeAsFlow().collect(model::cancelIntent)
        }

        lifecycleScope.launchWhenStarted {
            model.states().collect(::render)
        }

        setContent {
            JsonPlaceholderTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}