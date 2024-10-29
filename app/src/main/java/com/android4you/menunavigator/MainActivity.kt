package com.android4you.menunavigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.android4you.menunavigator.screens.ContainerScreen
import com.android4you.menunavigator.ui.theme.MenuNavigatorComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MenuNavigatorComposeTheme {
                ContainerScreen()
            }
        }
    }
}

