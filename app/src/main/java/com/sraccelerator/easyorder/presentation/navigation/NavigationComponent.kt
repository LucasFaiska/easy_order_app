package com.sraccelerator.easyorder.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigator: Navigator
) {
    val launchedEffectNavigationLabel = "navigation"

    LaunchedEffect(launchedEffectNavigationLabel) {
        navigator.navTarget.onEach { navArguments ->
            try {
                navArguments?.let {
                    processNavigation(navArguments, navController)
                }
            } catch (e: Exception) {
                Log.e("NavigationComponent", "Error processing navigation: ${e.message}")
            }
        }
    }
}

private fun processNavigation(navArguments: NavigationArgs, navController: NavHostController) {
    when(navArguments) {
        NavigationArgs.NavigateBack -> navController.navigateBack()
        is NavigationArgs.NavigateToDestination -> navController.navigateToDestination(navArguments)
    }
}

private fun NavHostController.navigateToDestination(navArguments: NavigationArgs.NavigateToDestination) {
    if (navArguments.cleanBackStack) {
        currentDestination?.parent?.startDestinationRoute?.let { startRoute ->
            navigate(route = navArguments.route) {
                popUpTo(startRoute) {
                    inclusive = navArguments.inclusive
                }
                launchSingleTop = navArguments.launchSingleTop
            }
        }
    } else {
        navigate(route = navArguments.route)
    }
}

private fun NavHostController.navigateBack() {
    currentDestination?.parent?.startDestinationRoute?.let { startDestinationRoute ->
        currentDestination?.route?.let { currentDestinationRoute ->
            if (startDestinationRoute != currentDestinationRoute) {
                popBackStack()
            }
        }
    }
}