package com.example.findyourapi.ui.LandingPage

import android.location.Location
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findyourapi.model.APIs
import com.example.findyourapi.model.Entrie
import com.example.findyourapi.ui.theme.ContainerBg
import com.example.findyourapi.ui.theme.Shapes
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun LandingPageListView(viewModel: LandingPageViewModel,entries: State<List<Entrie>>){

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
        )
    )
    val coroutineScope = rememberCoroutineScope()


    val ent = Entrie("API Name","Api Desc","Api auth","Api https","Cors","Link","Category")

    var entrie by remember { mutableStateOf(ent) }

    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        modifier = Modifier.padding(horizontal = 8.dp),
        backgroundColor = MaterialTheme.colors.background,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheet(entrie)
        }
    ) {
        ApiLst(entries = entries.value){
            coroutineScope.launch {
                entrie = it
                bottomSheetScaffoldState.bottomSheetState.expand()
                if (bottomSheetScaffoldState.bottomSheetState.isAnimationRunning){
                    bottomSheetScaffoldState.bottomSheetState.collapse()

                }
            }
        }
    }
}

@Composable
fun ApiLst(entries: List<Entrie>,onClick: (entrie: Entrie) -> Unit){
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        items(
            items = entries,
            itemContent = {
                ApiItem(entrie = it){entrie->
                    onClick(entrie)
                }
            })
    }
}

@Composable
fun ApiItem(entrie: Entrie,onClick: (entrie: Entrie) -> Unit){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment= Alignment.Start,
        modifier = Modifier
            .clickable{onClick(entrie)}
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 8.dp)
            .background(ContainerBg, shape = Shapes.large)
            .shadow(elevation = 1.5.dp)
            .padding(20.dp)
    ) {
        Text(text = entrie.API, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White, modifier = Modifier.padding(3.dp))
        Text(text = entrie.Description,fontSize = 16.sp, fontWeight = FontWeight.ExtraLight, color = Color.White, modifier = Modifier.padding(3.dp))
        Text(text = entrie.Category,fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.White, modifier = Modifier.padding(3.dp))
    }

}
