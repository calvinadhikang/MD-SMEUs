package com.bangkit.smeus.ui.screen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bangkit.smeus.ui.api.SmeResponseList
import com.bangkit.smeus.ui.components.ButtonForm
import com.bangkit.smeus.ui.components.CategoryItem
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.main.Main
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.theme.SMEUsTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
    ) {
        val context = LocalContext.current

        var listCategoryFake = listOf<Category>(
            Category(1, "Foods", false),
            Category(2,"Beverages", false),
            Category(3,"Craft", false)
        )

        AsyncImage(
            model = "https://assets.jatimnetwork.com/crop/0x0:0x0/750x500/webp/photo/2023/02/03/1223582901.jpeg",
            contentDescription = "",
            modifier = modifier.height(300.dp)
        )
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = "Judul",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "<Rp 25.000",
                fontSize = 26.sp,
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
                modifier = modifier
                    .padding(top = 8.dp, start = 0.dp, bottom = 8.dp)
            ) {
                items(items = listCategoryFake, key = { it -> it.id }) { it ->
                    CategoryItem(
                        id = it.id,
                        text = it.text,
                        selected = it.selected,
                        onClick = { it ->

                        }
                    )
                }
            }
            Text(
                text = "description",
                modifier = modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Similar",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
                modifier = modifier
                    .padding(top = 8.dp, start = 0.dp, bottom = 8.dp)
            ) {
//                items(items = smeList.value, key = { it -> it.indexPlace }) { it ->
//                    DestinationItem(
//                        id = it.indexPlace,
//                        image = it.image,
//                        name = it.nameSmes,
//                        goods = it.goods,
//                        onClick = {
//
//                        }
//                    )
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SMEUsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            DetailScreen()
        }
    }
}