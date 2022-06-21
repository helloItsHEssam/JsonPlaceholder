package com.iamhessam.jsonplaceholder.ui.main

import android.os.Bundle
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
import com.iamhessam.jsonplaceholder.ui.main.home.models.HomeIntent
import com.iamhessam.jsonplaceholder.ui.theme.JsonPlaceholderTheme
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge

class MainActivity : ComponentActivity() {

//    private val model: HomeModel by HomeModel()
    fun intents(): Flow<HomeIntent> = merge(
        initialIntent(),
        refreshIntent(),
    )

    private fun initialIntent(): Flow<HomeIntent> = flowOf(HomeIntent.Initial)
    private fun refreshIntent(): Flow<HomeIntent> = callbackFlow {
        trySend(HomeIntent.PullToRefresh)
        awaitClose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JsonPlaceholderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(name = "HEssam") {
                        refreshIntent()
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
                Text(text =  name, color = Color.Magenta)
                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "Click Here", color = Color.Green)
            }
        }
    }
}