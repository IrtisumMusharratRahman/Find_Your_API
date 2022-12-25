package com.example.findyourapi.ui.LandingPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findyourapi.model.APIs
import com.example.findyourapi.model.Entrie
import com.example.findyourapi.ui.theme.ContainerBg
import com.example.findyourapi.ui.theme.Shapes

@Composable
fun ApiLst(entries: List<Entrie>){
    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        items(
            items = entries,
            itemContent = {
                ApiItem(entrie = it)
            })
    }
}

@Composable
fun ApiItem(entrie: Entrie){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment= Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(ContainerBg, shape = Shapes.large)
            .shadow(elevation = 1.5.dp)
            .padding(20.dp)
    ) {
        Text(text = entrie.API, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(3.dp))
        Text(text = entrie.Description,fontSize = 16.sp, fontWeight = FontWeight.ExtraLight, modifier = Modifier.padding(3.dp))
        Text(text = entrie.Category,fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(3.dp))
//        Text(text = entrie.Auth)
//        Text(text = entrie.Cors)
//        Text(text = entrie.HTTPS)
//        Text(text = entrie.Link)
    }

}