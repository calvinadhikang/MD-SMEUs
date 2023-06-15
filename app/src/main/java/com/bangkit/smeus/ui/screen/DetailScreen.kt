package com.bangkit.smeus.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.UserPreference
import com.bangkit.smeus.ui.components.DestinationItem
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.theme.SMEUsTheme

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = viewModel(),
    modifier: Modifier = Modifier,
    smeId: String,
    navigateBack : () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    val context = LocalContext.current
    val user = UserPreference(context).getUser()

    val sme = viewModel.sme.collectAsState()
    val similar = viewModel.similarSme.collectAsState()
    val isFavorite = viewModel.isFavorite.collectAsState()
    viewModel.fetchSME(smeId, user.email)
    viewModel.checkIsFavorite(smeId, user.email)

    var iconFavorite = if (isFavorite.value) {
        Icons.Default.Favorite
    }else{
        Icons.Default.FavoriteBorder
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
    ) {

        var listCategoryFake = listOf<Category>(
            Category(1, "Foods", false),
            Category(2,"Beverages", false),
            Category(3,"Craft", false)
        )

        Box {
            AsyncImage(
                model = sme.value.image,
                contentDescription = "",
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
            )
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = modifier
                    .align(Alignment.TopStart)
                    .padding(start = 8.dp, top = 8.dp)
                    .clickable {
                        navigateBack()
                    }
            )
        }
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Row {
                Text(
                    text = sme.value.nameSmes,
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = modifier.weight(1F)
                )
                Icon(
                    imageVector = iconFavorite,
                    tint = Color.Red,
                    contentDescription = "",
                    modifier = modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.updateWishlist(context)
                            viewModel.toggleFavorite()
                        }
                )
            }
            Text(
                text = sme.value.priceRange,
                fontSize = 26.sp,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {
                Row(
                    modifier = modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "", tint = Color.White, modifier = modifier.padding(end = 8.dp))
                    Text(text = sme.value.city, color = Color.White)
                }
                Row(
                    modifier = modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_category_24), contentDescription = "", tint = Color.White, modifier = modifier.padding(end = 8.dp))
                    Text(text = sme.value.goods, color = Color.White)
                }
            }
//            IconText(text = "city", icon = Icons.Default.LocationOn)
//            Row(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(top = 8.dp, start = 0.dp, bottom = 8.dp)
//            ) {
//                IconText(text = "goods", iconInt = R.drawable.baseline_category_24, icon = iconFavorite)
//            }
            Text(
                text = sme.value.description,
                modifier = modifier.padding(bottom = 24.dp)
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
                items(items = similar.value, key = { it -> it.indexPlace }) { sme ->
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
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SMEUsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            DetailScreen(smeId = "BD_07",navigateBack = {}, navigateToDetail = {})
        }
    }
}