package com.bangkit.smeus.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.UserPreference
import com.bangkit.smeus.ui.components.CategoryItem
import com.bangkit.smeus.ui.components.DestinationItem
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.theme.SMEUsTheme

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = ExploreViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToSearch: (String) -> Unit,
) {
    val context = LocalContext.current
    var searchText by rememberSaveable { mutableStateOf("") }

    val smeList = viewModel.smeList.collectAsState(emptyList())
    viewModel.fetchSME(context)

    val preference = UserPreference(context)
    val user = preference.getUser()

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
                    text = "Hello, ${user.name}",
                    modifier = modifier
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    text = "Where to Next",
                    modifier = modifier
                )
            }
        }
        Spacer(modifier = modifier.height(16.dp))
        InputForm(
            text = searchText,
            label = "Search",
            errorText = "",
            onValueChange = {
                searchText = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = modifier.clickable {
                        navigateToSearch(searchText)
                    }
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
            items(items = Category.listCategory) {
                CategoryItem(text = it.text, image = it.image, onClick = {})
            }
        }
        Spacer(modifier = modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "Recommended SMEs", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = modifier.weight(1F))
            Text(text = "View All", color = Color.Gray)
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(top = 8.dp, start = 0.dp, bottom = 8.dp)
        ) {
            items(items = smeList.value, key = { it -> it.indexPlace }) { sme ->
                DestinationItem(
                    id = sme.indexPlace,
                    image = sme.image,
                    name = sme.nameSmes,
                    goods = sme.goods,
                    onClick = {
                        navigateToDetail(sme.indexPlace)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SMEUsTheme {
        ExploreScreen(navigateToDetail = {}, navigateToSearch = {})
    }
}