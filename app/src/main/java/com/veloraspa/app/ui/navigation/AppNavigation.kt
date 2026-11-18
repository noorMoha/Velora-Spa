package com.veloraspa.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veloraspa.app.ui.screens.OurSpecialistsScreen
import com.veloraspa.app.ui.screens.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OurSpecialists : Screen("our_specialists")
    object AdminSpecialists : Screen("admin_specialists")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(
                onGetStartedClick = {
                },
                onLoginClick = {

                }
            )
        }

        composable(Screen.OurSpecialists.route) {
            OurSpecialistsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSearchClick = {

                },
                onSelectClick = { specialist ->

                }
            )
        }

        composable(Screen.AdminSpecialists.route) {

        }
    }
}