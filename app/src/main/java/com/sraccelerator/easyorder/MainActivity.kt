package com.sraccelerator.easyorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.sraccelerator.easyorder.presentation.navigation.AppGraph
import com.sraccelerator.easyorder.presentation.navigation.Navigator
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGraph(navigator, rememberNavController())
        }
    }
}