package com.aherrera.fakelogin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aherrera.fakelogin.ui.screen.welcome.WelcomeScreen

object NavDestinations {
    const val WELCOME_SCREEN = "welcome_screen"
    const val LOGIN_SCREEN = "login_screen"
}

@Composable
fun FakeLoginNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavDestinations.WELCOME_SCREEN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = NavDestinations.WELCOME_SCREEN
        ) {
            WelcomeScreen()
        }
    }

}

private class FakeLoginActions(
    navController: NavHostController
) {
    val navigateToLogin: () -> Unit = {
        navController.navigate(NavDestinations.LOGIN_SCREEN)
    }
}