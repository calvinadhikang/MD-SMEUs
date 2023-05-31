package com.bangkit.smeus.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryItem(
    id: Int,
    text: String,
    selected: Boolean,
    onClick: (it: Int) -> Unit,
    modifier: Modifier = Modifier
){
    var flag by rememberSaveable { mutableStateOf(selected) }
    var container = if (flag) Color.Blue else Color.LightGray

    OutlinedButton(
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = container
        ),
        border = BorderStroke(1.dp, Color.Transparent),
        onClick = {
            flag = !flag
            onClick(id)
        },
        modifier = modifier,
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownPreview() {
    MaterialTheme {
        CategoryItem(1, "Foods", false, onClick = {})
    }
}