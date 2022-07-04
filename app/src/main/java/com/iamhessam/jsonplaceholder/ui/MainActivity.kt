package com.iamhessam.jsonplaceholder.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamhessam.jsonplaceholder.ui.navigation.BottomBarScreen
import com.iamhessam.jsonplaceholder.ui.navigation.graph.NavGraph
import com.iamhessam.jsonplaceholder.ui.screen.main.home.HomeScreen
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.*
import com.iamhessam.jsonplaceholder.ui.screen.main.mvi.MviView
import com.iamhessam.jsonplaceholder.ui.screen.main.profile.ProfileScreen
import com.iamhessam.jsonplaceholder.ui.screen.main.settings.SettingScreen
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavHost(navController = navController, startDestination = BottomBarScreen.Home.route, Modifier.padding(it)) {
            composable(BottomBarScreen.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(BottomBarScreen.Settings.route) {
                SettingScreen(navController = navController)
            }

            composable(BottomBarScreen.Profile.route) {
                ProfileScreen(navController = navController)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Settings,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}