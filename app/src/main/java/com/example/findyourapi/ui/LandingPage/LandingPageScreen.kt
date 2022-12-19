package com.example.findyourapi.ui.LandingPage

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.findyourapi.model.Entrie


@Composable
fun LandingPageScreen(
    viewModel: LandingPageViewModel = viewModel()
){
    val apis = viewModel.apis.collectAsState()

    LazyColumn() {
        items(
            items = apis.value.entries,
            itemContent = {
                ApiItem(entrie = it)
            })
    }
}

@Composable
fun ApiItem(entrie: Entrie){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment= Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp).border(width = 1.dp, shape = RectangleShape, color = Color.Gray)
    ) {
        Text(text = entrie.API)
        Text(text = entrie.Auth)
        Text(text = entrie.Cors)
        Text(text = entrie.HTTPS)
        Text(text = entrie.Category)
        Text(text = entrie.Description)
        Text(text = entrie.Link)
    }

}