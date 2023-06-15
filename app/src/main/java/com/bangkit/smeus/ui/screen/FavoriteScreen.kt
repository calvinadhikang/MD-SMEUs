package com.bangkit.smeus.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.smeus.ui.UserPreference
import com.bangkit.smeus.ui.components.DestinationItem
import com.bangkit.smeus.ui.components.InputForm

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(),
    navigateToDetail: (String) -> Unit
){
    val context = LocalContext.current
    val preference = UserPreference(context)
    val user = preference.getUser()
    
    var searchText by rememberSaveable { mutableStateOf("") }

    val sme = viewModel.smeList.collectAsState()
    viewModel.fetchSME(user.email)

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
fun FavoriteScreenPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            FavoriteScreen(navigateToDetail = {})
        }
    }
}