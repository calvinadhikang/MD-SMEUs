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
    Column(
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
            IconText(text = name, icon = Icons.Default.LocationOn)
            IconText(text = goods, icon = Icons.Default.Settings)
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