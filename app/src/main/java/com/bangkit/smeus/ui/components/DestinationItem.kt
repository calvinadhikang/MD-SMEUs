package com.bangkit.smeus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bangkit.smeus.R

@Composable
fun DestinationItem(
    modifier: Modifier = Modifier,
    id: String,
    image: String,
    name: String,
    goods: String,
    onClick: (it: String) -> Unit
){
    Box(
        modifier = modifier
            .width(265.dp)
            .clickable {
                onClick(id)
            }
    ) {
        AsyncImage(
            model = image,
            contentDescription = image,
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(265.dp)
        )
        Box(
            modifier = modifier
                .padding(bottom = 4.dp)
                .fillMaxWidth(0.9F)
                .height(80.dp)
                .align(Alignment.BottomCenter)
        ){
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                softWrap = true,
                maxLines = 1,
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterStart)
            )
            IconText(
                text = goods,
                iconInt = R.drawable.baseline_category_24,
                icon = Icons.Default.Search,
                modifier = modifier
                    .padding(start = 30.dp, bottom = 18.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(start = 8.dp, end = 10.dp)
                    .align(Alignment.TopStart)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DestinationItemPreview() {
    MaterialTheme {
        DestinationItem(
            image = "https://storage.googleapis.com/capstone-smeus/drive-gambar-sme/BD_49.jpg",
            id = "1",
            name = "Name",
            goods = "Souvenir",
            onClick = {

            }
        )
    }
}