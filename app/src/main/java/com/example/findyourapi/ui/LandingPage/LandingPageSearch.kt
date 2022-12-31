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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findyourapi.ui.theme.ContainerBg
import com.example.findyourapi.ui.theme.MaterialDefault
import com.example.findyourapi.ui.theme.Shapes


@Composable
fun SearchBar(
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf(value = "")
    }
    var hintText by remember {
        mutableStateOf("Search...")
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
            text = it
            onSearch(it)
        }
    )
}


@Composable
fun Filter(onCLick: () -> Unit = {}) {
    Button(
        modifier = Modifier
            .wrapContentSize()
            .padding(2.dp)
            .padding(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = ContainerBg),
        shape = CircleShape,
        onClick = { onCLick() }) {
        Image(
            painter = painterResource(id = com.example.findyourapi.R.drawable.ic_baseline_filter_alt_24),
            contentDescription = "",
            modifier = Modifier.size(height = 32.dp, width = 25.dp)
        )
    }
}


@Composable
fun ApplyFilter(viewModel: LandingPageViewModel) {

    val categories = viewModel.categories.collectAsState()
    val temp = categories.value.categories


    val options = ArrayList<String>()
    if (temp.isNotEmpty()) {
        options.add("All")
        for (cat in temp) {
            options.add(cat)
        }
    }
    val order = arrayOf("Default", "Ascending", "Descending")

    var sortOrder by remember { mutableStateOf("") }
    var sortCategory by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(color = ContainerBg, shape = Shapes.medium)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp)
            .padding(15.dp)
    ) {
        Column {

            Text(
                text = "Search Filters",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(2.dp)
            )
            DropdownFilter(type = "Sort By", options = order.toList()) {
                sortOrder = it
            }
            DropdownFilter(type = "Category", options = options) {
                sortCategory = it
            }
            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .wrapContentSize(),
                onClick = {
                    viewModel.applyFilter(sortOrder, sortCategory)
                }
            ) {
                Text(text = "Apply", color = Color.White)
            }
        }

    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownFilter(type: String, options: List<String>, getSelectedValue: (String) -> Unit = {}) {

    var selectedItem by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(0.dp)
            .padding(2.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = type)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }) {
            Row(
                modifier = Modifier
                    .background(color = MaterialDefault, shape = Shapes.medium)
                    .padding(0.dp)
                    .padding(2.dp)
                    .fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(text = selectedItem)
                Icon(
                    painter = painterResource(id = com.example.findyourapi.R.drawable.ic_baseline_keyboard_arrow_down_24),
                    contentDescription = null
                )
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {

                options.forEach {
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = it
                            getSelectedValue(selectedItem)
                            expanded = false
                        }) {
                        Text(text = it)
                    }
                }

            }
        }
    }
}