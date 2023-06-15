package com.bangkit.smeus.ui.components

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.R

@Composable
fun IconText(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    iconInt:Int? = null,
    textColor: Color = Color.White,
    iconTint: Color = Color.White,
    fontSize: TextUnit = 10.sp
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(4.dp)
    ) {
        if (iconInt == null){
            Icon(imageVector = icon, contentDescription = "", tint = iconTint)
        }else{
            Icon(painter = painterResource(id = iconInt), contentDescription = "", tint = iconTint)
        }
        Text(text = text, fontSize = fontSize, color = textColor, softWrap = true)
    }
}

@Preview(showBackground = true)
@Composable
fun IconTextPreview() {
    MaterialTheme {
        IconText(
            text = "Test",
            icon = Icons.Default.AccountCircle
        )
    }
}