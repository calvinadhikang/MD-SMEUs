package com.bangkit.smeus.ui.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.UserPreference
import com.bangkit.smeus.ui.components.DestinationItem
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.model.Destination
import com.bangkit.smeus.ui.model.PriceRange
import com.bangkit.smeus.ui.theme.SMEUsTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    key:String = "",
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(),
    navigateToDetail: (smeId: String) -> Unit
){
    val context = LocalContext.current
    val preference = UserPreference(context)
    val user = preference.getUser()

    var searchText by rememberSaveable { mutableStateOf(key) }

    val sme = viewModel.smeList.collectAsState()
    viewModel.fetchSME(user.email)

    val categoryList = Category.listCategory
    val priceRangeList = PriceRange.listPriceRange

    var expandedCategory by remember { mutableStateOf(false) }
    var expandedPriceRange by remember { mutableStateOf(false) }

    var selectedTextCategory by remember { mutableStateOf(categoryList[0].text) }
    var selectedTextPriceRange by remember { mutableStateOf(priceRangeList[0].text) }

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
            text = searchText,
            label = "Search",
            errorText = "",
            onValueChange = {
                searchText = it
                viewModel.filterSME(it)
            },
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
                Box {
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
                                    text = { Text(text = item.text) },
                                    onClick = {
                                        selectedTextCategory = item.text
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
                Box {
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
                                    text = { Text(text = item.text) },
                                    onClick = {
                                        selectedTextPriceRange = item.text
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
            text = "Showing ${sme.value.size} Results",
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 0.dp, bottom = 8.dp)
        ){
            items(items = sme.value, key = {it -> it.indexPlace}){sme ->
                DestinationItem(
                    id = sme.indexPlace,
                    image = sme.image,
                    name = sme.nameSmes,
                    goods = sme.goods,
                    onClick = {
                        navigateToDetail(sme.indexPlace)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SMEUsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            SearchScreen(navigateToDetail = {})
        }
    }
}