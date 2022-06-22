package com.iamhessam.jsonplaceholder.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.iamhessam.jsonplaceholder.ui.main.home.models.*
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviView
import com.iamhessam.jsonplaceholder.ui.theme.JsonPlaceholderTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class MainActivity() : ComponentActivity(),
    MviView<HomeResult, HomeProcessor, HomeAction, HomeIntent, HomeViewState> {

//    private val model: HomeModel by HomeModel()

    private val model = HomeModel()
    override val intents: Flow<HomeIntent> = merge(
        initialIntent(),
        refreshIntent()
    )

    override fun render(state: HomeViewState) {
        Log.d("new Stateeeeeeee", state.toString())
    }

    private val btnChannel = Channel<HomeIntent>()

    private fun initialIntent(): Flow<HomeIntent> = flowOf(HomeIntent.Initial)
    private fun refreshIntent(): Flow<HomeIntent> = callbackFlow {
        trySend(HomeIntent.PullToRefresh)
        awaitClose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            intents.collect(model::processorIntent)
        }

        lifecycleScope.launchWhenStarted {
            model.states().collect(::render)
        }

        setContent {
            JsonPlaceholderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(name = "HEssam") {
//                        val r: Flow<HomeIntent> = callbackFlow {
//                            trySend(HomeIntent.PullToRefresh)
//                            awaitClose()
//                        }

                        model.processorIntent(HomeIntent.PullToRefresh)
//                        refreshIntent()
//                        btnChannel.trySend(HomeIntent.PullToRefresh)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(name: String, click: () -> Unit) {
    Column(Modifier.padding(10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = click) {
                Text(text = name, color = Color.Magenta)
                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "Click Here", color = Color.Green)
            }
        }
    }
}