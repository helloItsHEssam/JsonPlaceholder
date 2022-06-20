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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamhessam.jsonplaceholder.ui.theme.JsonPlaceholderTheme

class MainActivity : ComponentActivity() {
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