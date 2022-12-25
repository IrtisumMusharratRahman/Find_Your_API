package com.example.findyourapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.findyourapi.model.Entrie
import com.example.findyourapi.ui.LandingPage.ApiItem
import com.example.findyourapi.ui.LandingPage.LandingPageScreen
import com.example.findyourapi.ui.theme.FindYourAPITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindYourAPITheme {
                LandingPageScreen()
            }
        }
    }
}




