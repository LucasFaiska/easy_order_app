package com.sraccelerator.easyorder.presentation.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

interface Navigator {
    val navTarget: SharedFlow<NavigationArgs?>

    suspend fun navigateTo(
        navTarget: NavigationRoute,
        cleanBackStack: Boolean = false,
        inclusive: Boolean = false,
        launchSingleTop: Boolean = true
    )

    suspend fun navigateBack()
}

class NavigatorImpl @Inject constructor() : Navigator {

    private val _navTarget = MutableSharedFlow<NavigationArgs?>()
    override val navTarget = _navTarget.asSharedFlow()

    override suspend fun navigateTo(
        navTarget: NavigationRoute,
        cleanBackStack: Boolean,
        inclusive: Boolean,
        launchSingleTop: Boolean
    ) {
        _navTarget.emit(
            NavigationArgs.NavigateToDestination(
                route = navTarget,
                cleanBackStack = cleanBackStack,
                inclusive = inclusive,
                launchSingleTop = launchSingleTop
            )
        )
    }

    override suspend fun navigateBack() {
        _navTarget.emit(NavigationArgs.NavigateBack)
    }

}