package com.aherrera.fakelogin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aherrera.fakelogin.ui.screen.login.LoginScreen
import com.aherrera.fakelogin.ui.screen.signUp.SignUpScreen
import com.aherrera.fakelogin.ui.screen.welcome.WelcomeScreen

object NavDestinations {
    const val WELCOME_SCREEN = "welcome_screen"
    const val SIGN_UP_SCREEN = "sign_up_screen"
    const val LOGIN_SCREEN = "login_screen"
}

@Composable
fun FakeLoginNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavDestinations.WELCOME_SCREEN
) {
    val actions = remember(navController) { FakeLoginActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = NavDestinations.WELCOME_SCREEN
        ) {
            WelcomeScreen(
                goToLogin = actions.navigateToLogin,
                goToSignUp = actions.navigateToSignUp
            )
        }

        composable(
            route = NavDestinations.SIGN_UP_SCREEN
        ) {
            SignUpScreen(
                goToWelcome = actions.navigateToWelcome
            )
        }

        composable(
            route = NavDestinations.LOGIN_SCREEN
        ) {
            LoginScreen(
                goToWelcome = actions.navigateToWelcome
            )
        }
    }

}

private class FakeLoginActions(
    navController: NavHostController
) {
    val navigateToLogin: () -> Unit = {
        navController.navigate(NavDestinations.LOGIN_SCREEN)
    }

    val navigateToSignUp: () -> Unit = {
        navController.navigate(NavDestinations.SIGN_UP_SCREEN)
    }

    val navigateToWelcome: () -> Unit = {
        navController.navigate(NavDestinations.WELCOME_SCREEN){
            popUpTo(NavDestinations.WELCOME_SCREEN) {
                inclusive = true
            }
        }
    }
}