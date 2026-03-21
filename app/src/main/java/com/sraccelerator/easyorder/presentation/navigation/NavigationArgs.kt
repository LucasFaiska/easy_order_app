package com.sraccelerator.easyorder.presentation.navigation

sealed interface NavigationArgs {

    data class NavigateToDestination(
        val route: NavigationRoute,
        val cleanBackStack: Boolean = false,
        val inclusive: Boolean = false,
        val launchSingleTop: Boolean = true
    ) : NavigationArgs

    data object NavigateBack : NavigationArgs
}