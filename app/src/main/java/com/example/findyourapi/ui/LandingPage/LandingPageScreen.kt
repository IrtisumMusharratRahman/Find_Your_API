package com.example.findyourapi.ui.LandingPage


import android.util.Log
import androidx.compose.foundation.*



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.material.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.findyourapi.R

import com.example.findyourapi.model.Entrie
import com.example.findyourapi.ui.theme.ContainerBg
import com.example.findyourapi.ui.theme.MaterialDefault
import com.example.findyourapi.ui.theme.Shapes

import me.onebone.toolbar.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LandingPageScreen(
    viewModel: LandingPageViewModel = viewModel()
){
    val apis = viewModel.apis.collectAsState()
    viewModel.getAPIs()
    viewModel.getCategories()
    val entries = viewModel.entries.collectAsState()
    val isFiltered = viewModel.isFiltered.collectAsState()


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val state = rememberCollapsingToolbarScaffoldState()

        val lazyListState = rememberLazyListState()
        val isScrolled = lazyListState.isScrollInProgress

        CollapsingToolbarScaffold(
            modifier = Modifier,
            state = state, scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
                Column() {
                    Row(
                        modifier = Modifier
                            .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
                            .fillMaxWidth()
                            .height(if (isScrolled) 0.dp else 60.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SearchBar(){
                            viewModel.getSearchedApiList(text = it)
                        }
                        Filter(){
                            viewModel.changeFilterStatus()
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 0.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
                            .fillMaxWidth()
                            .height(if (isFiltered.value) 150.dp else 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ApplyFilter(viewModel)
                    }
                }
            }
        ) {
            if (apis.value.count!=0){
                LandingPageListView(viewModel = viewModel, entries = entries)
            }else{
                LoadingSpinner()
            }
        }
    }
}




@Composable
fun LoadingSpinner(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            color = ContainerBg,
            strokeWidth = 8.dp
        )
    }

}




@Preview
@Composable
fun DefPrev(){
    val ent = Entrie("API Name","Api Desc","Api auth","Api https","Cors","Link-----------------------------------------","Category")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){

    }
}

