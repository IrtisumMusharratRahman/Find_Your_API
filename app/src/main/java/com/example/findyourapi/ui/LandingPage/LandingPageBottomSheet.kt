package com.example.findyourapi.ui.LandingPage

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findyourapi.model.Entrie
import com.example.findyourapi.ui.theme.BottomSheetBG
import com.example.findyourapi.ui.theme.ContainerBg
import com.example.findyourapi.ui.theme.Shapes
import com.example.findyourapi.ui.theme.bg2

@Composable
fun BottomSheet(entrie: Entrie){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .background(color = BottomSheetBG, shape = Shapes.large)
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.65f)
            .padding(0.dp)
            .padding(10.dp)
            .border(width = 3.dp, color = MaterialTheme.colors.background, shape = Shapes.large)
    ) {
        Box(
            modifier = Modifier
                .background(color = ContainerBg, shape = Shapes.large)
                .fillMaxSize()
                .padding(0.dp)
//                .shadow(10.dp, Shapes.large)
                .padding(vertical = 30.dp, horizontal = 40.dp)

        ) {
            Column() {
                Text(text = entrie.API, fontSize = 28.sp, fontWeight = FontWeight.SemiBold, color = Color.White, modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp))
                Text(text = "DESC: ${entrie.Description}", fontSize = 16.sp, fontWeight = FontWeight.Light, color = Color.White, modifier = Modifier.padding(5.dp))
                Text(text = "CATEGORY: ${entrie.Category}", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.White, modifier = Modifier.padding(5.dp))
                Text(text = "CORS: ${entrie.Cors}", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.White, modifier = Modifier.padding(5.dp))
                Text(text = "AUTH: ${entrie.Auth}", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.White, modifier = Modifier.padding(5.dp))
                Text(text = "HTTPS: ${entrie.HTTPS}", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.White, modifier = Modifier.padding(5.dp))
                Text(text = "LINK: ${entrie.Link}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFb3d9ff),
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(entrie.Link))
                            context.startActivity(intent)
                        }
                )

            }
        }


    }
}