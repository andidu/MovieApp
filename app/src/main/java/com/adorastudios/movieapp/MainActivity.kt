@file:OptIn(ExperimentalAnimationApi::class)

package com.adorastudios.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.adorastudios.movieapp.presentation.screens.details_screen.DetailsScreen
import com.adorastudios.movieapp.presentation.screens.main_screen.MainScreen
import com.adorastudios.movieapp.presentation.utils.FocusSurface
import com.adorastudios.movieapp.presentation.utils.Screen
import com.adorastudios.movieapp.ui.theme.MovieAppTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                FocusSurface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberAnimatedNavController()

                    AnimatedNavHost(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        startDestination = Screen.MainScreen.route,
                    ) {
                        composable(
                            route = Screen.MainScreen.route,
                            enterTransition = {
                                fadeIn(animationSpec = tween(300))
                            },
                            exitTransition = {
                                fadeOut(animationSpec = tween(300))
                            },
                        ) {
                            MainScreen(navController = navController)
                        }
                        composable(
                            route = Screen.DetailsScreen.route +
                                "?${Screen.PARAM_ID}={${Screen.PARAM_ID}}",
                            enterTransition = {
                                fadeIn(animationSpec = tween(300))
                            },
                            exitTransition = {
                                fadeOut(animationSpec = tween(300))
                            },
                            arguments = listOf(
                                navArgument(
                                    name = Screen.PARAM_ID,
                                ) {
                                    type = NavType.LongType
                                    defaultValue = -1L
                                },
                            ),
                        ) {
                            DetailsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}
