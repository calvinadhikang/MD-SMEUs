package com.bangkit.smeus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.R

@Composable
fun DestinationItem(
    modifier: Modifier = Modifier,
    image: Int,
    name: String,
    location: String,
    category: String,
    price: String,
    onClick: () -> Unit
){
    Column(
        modifier = modifier
            .width(265.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = modifier
                .fillMaxWidth()
                .height(265.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            IconText(text = location, icon = Icons.Default.LocationOn)
            IconText(text = category, icon = Icons.Default.Settings)
            IconText(text = price, icon = Icons.Default.PlayArrow)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DestinationItemPreview() {
    MaterialTheme {
        DestinationItem(
            image = R.drawable.ic_launcher_background,
            name = "Name",
            location = "Location",
            category = "Category",
            price = "Price K"
        ) {

        }
    }
}