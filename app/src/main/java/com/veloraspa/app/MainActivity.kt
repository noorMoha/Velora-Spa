package com.veloraspa.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.veloraspa.app.ui.navigation.AppNavigation
import com.veloraspa.app.ui.theme.VeloraspaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VeloraspaTheme {
                AppNavigation()
            }
        }
    }
}