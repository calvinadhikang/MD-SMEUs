package com.bangkit.smeus.ui.user

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.components.DestinationItem
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.model.Destination

class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    User()
                }
            }
        }
    }
}

var listDestinationFake = listOf<Destination>(
    Destination(1, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
    Destination(2, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
    Destination(3, "Destination1", category = "Category1", location = "Location1", price = "Price1", photo = R.drawable.ic_launcher_background),
)

@Composable
fun User(
    modifier: Modifier = Modifier
) {
    var searchText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.weight(1F)
            ) {
                Text(
                    color = Color.DarkGray,
                    text = "Hello, Dikang",
                    modifier = modifier
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    text = "Where to Next",
                    modifier = modifier
                )
            }
            Image(
                imageVector = Icons.Default.Notifications,
                contentDescription = "",
                modifier = modifier
                    .border(1.dp, Color.Gray, CircleShape)
                    .padding(8.dp)
            )
        }
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
        Spacer(modifier = modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "Categories", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = modifier.weight(1F))
            Text(text = "View All", color = Color.Gray)
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
            modifier = modifier
                .padding(top = 8.dp, start = 0.dp, bottom = 8.dp)
        ) {
            items(items = listDestinationFake, key = { it -> it.id }) { it ->
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
        Spacer(modifier = modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "Recommended Destination", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = modifier.weight(1F))
            Text(text = "View All", color = Color.Gray)
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = modifier
                .padding(top = 8.dp, start = 0.dp, bottom = 8.dp)
        ) {
            items(items = listDestinationFake, key = { it -> it.id }) { it ->
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
fun UserPreview() {
    MaterialTheme {
        User()
    }
}