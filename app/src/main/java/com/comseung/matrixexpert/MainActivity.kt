package com.comseung.matrixexpert

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
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
                                )
                            )
                        },

                        ) { paddingValues ->
                        MyAppNavHost(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        )
                    }


                }
            }
        }
    }

    @Composable
    private fun MyAppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
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