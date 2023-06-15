package com.bangkit.smeus.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.TextFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.components.CategoryItem
import com.bangkit.smeus.ui.components.DestinationItem
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.model.Destination
import com.bangkit.smeus.ui.theme.SMEUsTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
){
    var listDestinationFake = listOf<Destination>(
        Destination(1, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
        Destination(2, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
        Destination(3, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
        Destination(4, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
        Destination(5, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
        Destination(6, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
        Destination(7, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
        Destination(8, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
    )
    var listCategoryFake = listOf<Category>(
        Category(1, "Foods", false),
        Category(2,"Beverages", false),
        Category(3,"Craft", false),
        Category(4,"Goods", false),
        Category(5,"OMG", false),
        Category(6,"Bebas", false)
    )
    var listPriceFake = listOf<Category>(
        Category(2,"< 25K", false),
        Category(3,"25-50K", false),
        Category(4,"> 50K", false),
    )

    val categoryList = arrayOf("FnB", "Fashion", "Munchies", "Craft")
    val priceRangeList = arrayOf("Select Range","< 25K", "25K - 50K", ">50K")

    var expandedCategory by remember { mutableStateOf(false) }
    var expandedPriceRange by remember { mutableStateOf(false) }

    var selectedTextCategory by remember { mutableStateOf(categoryList[0]) }
    var selectedTextPriceRange by remember { mutableStateOf(priceRangeList[0]) }

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            text = "Search For SMEs",
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(16.dp))
        InputForm(
            text = "",
            label = "Search",
            errorText = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            }
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 8.dp)
        ){
            Column(
                modifier = modifier.weight(0.5F)
            ) {
                Text(
                    text = "Select Category",
                    fontWeight = FontWeight.SemiBold
                )
                Box() {
                    ExposedDropdownMenuBox(
                        expanded = expandedCategory,
                        onExpandedChange = {
                            expandedCategory = !expandedCategory
                        }
                    ) {
                        TextField(
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                textColor = Color.White
                            ),
                            value = selectedTextCategory,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory) },
                        )

                        ExposedDropdownMenu(
                            expanded = expandedCategory,
                            onDismissRequest = { expandedCategory = false }
                        ) {
                            categoryList.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedTextCategory = item
                                        expandedCategory = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = modifier.padding(8.dp))
            Column(
                modifier = modifier.weight(0.5F)
            ) {
                Text(
                    text = "Select Price Range",
                    fontWeight = FontWeight.SemiBold
                )
                Box() {
                    ExposedDropdownMenuBox(
                        expanded = expandedPriceRange,
                        onExpandedChange = {
                            expandedPriceRange = !expandedPriceRange
                        }
                    ) {
                        TextField(
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                textColor = Color.White
                            ),
                            value = selectedTextPriceRange,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPriceRange) },
                        )

                        ExposedDropdownMenu(
                            expanded = expandedPriceRange,
                            onDismissRequest = { expandedPriceRange = false }
                        ) {
                            priceRangeList.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedTextPriceRange = item
                                        expandedPriceRange = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        Text(
            text = "Showing 8 Results",
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ){
//            items(items = listDestinationFake, key = {it -> it.id}){
//                DestinationItem(
//                    image = it.photo,
//                    name = it.name,
//                    location = it.location,
//                    category = it.category,
//                    price = it.price,
//                    onClick = { }
//                )
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SMEUsTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            SearchScreen()
        }
    }
}