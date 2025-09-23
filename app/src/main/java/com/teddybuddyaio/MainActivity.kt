package com.teddybuddyaio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teddybuddyaio.data.FakeTeddyBuddyRepository
import com.teddybuddyaio.ui.screens.EmotionAnalysisScreen
import com.teddybuddyaio.ui.screens.HomeScreen
import com.teddybuddyaio.ui.screens.WelcomeScreen
import com.teddybuddyaio.ui.theme.TeddyBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeddyBuddyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    var uiState by remember { mutableStateOf(FakeTeddyBuddyRepository.loadState()) }

                    NavHost(
                        navController = navController,
                        startDestination = Routes.Welcome
                    ) {
                        composable(Routes.Welcome) {
                            WelcomeScreen(
                                onJoinClick = {
                                    navController.navigate(Routes.Home) {
                                        popUpTo(Routes.Welcome) { inclusive = true }
                                    }
                                },
                                onLoginClick = {
                                    navController.navigate(Routes.Home) {
                                        popUpTo(Routes.Welcome) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Routes.Home) {
                            HomeScreen(
                                uiState = uiState,
                                onNavigateToEmotion = {
                                    navController.navigate(Routes.EmotionAnalysis)
                                },
                                onToggleRoutine = { title, enabled ->
                                    uiState = uiState.copy(
                                        routines = uiState.routines.map { routine ->
                                            if (routine.title == title) routine.copy(isEnabled = enabled) else routine
                                        }
                                    )
                                }
                            )
                        }
                        composable(Routes.EmotionAnalysis) {
                            EmotionAnalysisScreen(report = uiState.emotionReport)
                        }
                    }
                }
            }
        }
    }
}

object Routes {
    const val Welcome = "welcome"
    const val Home = "home"
    const val EmotionAnalysis = "emotionAnalysis"
}
