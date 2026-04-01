package com.sraccelerator.easyorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.sraccelerator.easyorder.core.config.AppConfig
import com.sraccelerator.easyorder.presentation.navigation.AppGraph
import com.sraccelerator.easyorder.presentation.navigation.Navigator
import com.sraccelerator.easyorder.presentation.theme.EasyOrderTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var appConfig: AppConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasyOrderTheme(config = appConfig) {
                AppGraph(navigator, rememberNavController())
            }
        }
    }
}
