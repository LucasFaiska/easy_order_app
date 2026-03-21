package com.sraccelerator.easyorder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sraccelerator.easyorder.presentation.ui.category.list.CategoryListRoot
import kotlinx.serialization.Serializable

@Composable
fun AppGraph(navigator: Navigator, navController: NavHostController) {
    NavigationComponent(
        navController = navController,
        navigator = navigator
    )

    NavHost(
        navController = navController,
        startDestination = AppRoutes.CategoryList
    ) {
        composable<AppRoutes.CategoryList> {
            CategoryListRoot()
        }
    }
}

sealed interface AppRoutes : NavigationRoute {

    @Serializable
    data object CategoryList : AppRoutes
}