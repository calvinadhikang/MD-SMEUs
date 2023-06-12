package com.bangkit.smeus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonForm(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    onClick: () -> Unit
){
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun ButtonFormWithLoading(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    text: String,
    color: Color,
    onClick: () -> Unit
){
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (isLoading){
                CircularProgressIndicator(
                    color = Color.White
                )
            }
            Text(text = text, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonFormPreview() {
    MaterialTheme {
        ButtonFormWithLoading(text = "Button", onClick = {}, color = Color.Blue, isLoading = true)
    }
}