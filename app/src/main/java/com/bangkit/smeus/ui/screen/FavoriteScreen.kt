package com.bangkit.smeus.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.components.DestinationItem
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.model.Destination

@Composable
fun FavoriteScreen(
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

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            text = "My Favorites",
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
        Row(){

        }
        Text(
            text = "Showing 8 Results",
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ){
            items(items = listDestinationFake, key = {it -> it.id}){
                DestinationItem(
                    image = it.photo,
                    name = it.name,
                    location = it.location,
                    category = it.category,
                    price = it.price,
                    onClick = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            FavoriteScreen()
        }
    }
}