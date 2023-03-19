package com.comseung.matrixexpert

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.comseung.matrixexpert.presentation.add_matrix.AddMatrixScreen
import com.comseung.matrixexpert.presentation.home.HomeScreen
import com.comseung.matrixexpert.presentation.home.HomeViewModel
import com.comseung.matrixexpert.ui.theme.MatrixExpertTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {



            MatrixExpertTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Matrix Expert",
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                },
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor =
                                    MaterialTheme.colorScheme.primary
                                ),
                                actions = {
                                    IconButton(onClick = {
                                        navController.navigate("add_matrix")
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.AddCircle,
                                            contentDescription = "Share",
                                            tint = Color.White
                                        )
                                    }
                                }
                            )
                        },

                        ) { paddingValues ->
                        MyAppNavHost(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            navController = navController
                        )
                    }


                }
            }
        }
    }

    @Composable
    private fun MyAppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        startDestination: String = "home"
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("home") {
                HomeScreen(
                    onNavigationToAddMatrix = { navController.navigate("add_matrix") },
                    viewModel = hiltViewModel(),
                )
            }
            composable("add_matrix") {
                AddMatrixScreen(
                    onNavigationToHome = { navController.navigate("home") },
                    viewModel = hiltViewModel(),
                )
            }
        }
    }


}

private fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(orientation) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}