package com.example.findyourapi.ui.LandingPage

import android.widget.SearchView
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.rememberScrollableState


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.findyourapi.R
import com.example.findyourapi.model.APIs
import com.example.findyourapi.model.Entrie
import com.example.findyourapi.ui.theme.ContainerBg
import me.onebone.toolbar.*


@Composable
fun LandingPageScreen(
    viewModel: LandingPageViewModel = viewModel()
){
    val apis = viewModel.apis.collectAsState()
    viewModel.getAPIs()
    var entries:List<Entrie> = apis.value.entries

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val state = rememberCollapsingToolbarScaffoldState()

        val isScrolled = state.toolbarState.isScrollInProgress

        CollapsingToolbarScaffold(
            modifier = Modifier,
            state = state, scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
                Row(
                    modifier = Modifier
                        .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
                        .fillMaxWidth()
                        .height(if(isScrolled) 0.dp else 60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchBar(){
                        entries = viewModel.getFilteredApiList(text = it)
                    }
                    Filter()
                }
            }
        ) {

            ApiLst(entries = entries)

        }
    }
}




@Preview
@Composable
fun defPrev(){
    val ent = Entrie("API Name","Api Desc","Api auth","Api https","Cors","Link-----------------------------------------","Category")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        Filter()
    }
}