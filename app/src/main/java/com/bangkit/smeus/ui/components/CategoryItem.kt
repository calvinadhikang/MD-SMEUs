package com.bangkit.smeus.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.smeus.R

@Composable
fun CategoryItem(
    text: String,
    image: Int,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .clickable {
                onClick(text)
            }
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .height(100.dp)
                .width(100.dp)
        )
        Text(
            text = text,
            modifier = modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownPreview() {
    MaterialTheme {
        CategoryItem("Craft", R.drawable.craft, onClick = {})
    }
}