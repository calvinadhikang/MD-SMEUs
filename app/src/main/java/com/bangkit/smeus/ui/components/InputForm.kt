package com.bangkit.smeus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.smeus.ui.theme.SMEUsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    errorText: String,
    onValueChange: (it: String) -> Unit,
    leadingIcon: @Composable () -> Unit?
) {
    OutlinedTextField(
        leadingIcon = {
            leadingIcon()
        },
        value = text,
        label = {
            Text(text = label, color = Color.Black)
        },
        supportingText = {
            if (errorText != ""){
                Text(
                    text = errorText,
                    color = Color.Red
                )
            }
        },
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            containerColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    )
}

@Preview(showBackground = true)
@Composable
fun TextFormPreview() {
    SMEUsTheme() {
        InputForm(
            text = "",
            label = "Input",
            onValueChange = { },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "SearchIcon")
            },
            errorText = "Error bang"
        )
    }
}