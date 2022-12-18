package com.example.findyourapi.ui.LandingPage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlin.text.Typography.dagger

@Composable
fun LandingPageScreen(
    viewModel: LandingPageViewModel
){
    viewModel.apis.collectAsState()

}