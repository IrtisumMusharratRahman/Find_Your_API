package com.example.findyourapi.ui.LandingPage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findyourapi.ui.theme.ContainerBg
import com.example.findyourapi.ui.theme.Shapes


@Composable
fun SearchBar(
    onSearch: (String) -> Unit = {}
){
    var text by remember {
        mutableStateOf(value = "")
    }
    var hintText by remember {
        mutableStateOf("Search..")
    }
    BasicTextField(
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light
        ),
        singleLine = true,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(fraction = 0.8f)
            .padding(2.dp)
            .background(ContainerBg, CircleShape)
            .shadow(2.dp, CircleShape)
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .onFocusChanged {
                if (!it.isFocused) {
                    text = hintText
                } else {
                    text = ""
                }
            },
        value = text,
        onValueChange = {
            text=it
            onSearch(it)
        }
    )
}


@Composable
fun Filter(onCLick:() -> Unit = {}){
    Button(
        modifier = Modifier
            .wrapContentSize()
            .padding(2.dp)
            .padding(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = ContainerBg),
        shape = CircleShape,
        onClick = {onCLick()}) {
        Image(
            painter = painterResource(id = com.example.findyourapi.R.drawable.ic_baseline_filter_list_24),
            contentDescription = "",
            modifier = Modifier.size(height = 32.dp, width = 25.dp))
    }
}


@Composable
fun ApplyFilter(viewModel: LandingPageViewModel){

    Box(
        modifier = Modifier
            .background(color = ContainerBg, shape = Shapes.medium)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp)
            .padding(5.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.padding(0.dp).padding(2.dp)
            ) {
                Text(text = "Search Filters")
                MyUI()
            }
            Row(
                modifier = Modifier.padding(0.dp).padding(2.dp)
            ) {
                Text(text = "Sort By")
                MyUI()
            }
            Row(
                modifier = Modifier.padding(0.dp).padding(2.dp)
            ) {
                Text(text = "Category")
                MyUI()
            }
//            Text(text = "Search Filters")
//            Text(text = "Sort By")
//            Text(text = "Category")
//            MyUI()
        }

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyUI() {

    val contextForToast = LocalContext.current.applicationContext

    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    // the box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        // text field
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}
