package com.bangkit.smeus.ui.preference

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.ui.theme.SMEUsTheme
import com.bangkit.smeus.ui.user.UserActivity

class PreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMEUsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreferenceScreen()
                }
            }
        }
    }
}

@Composable
fun PreferenceScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var state by rememberSaveable { mutableStateOf(0)}

    Column() {
        when (state) {
            0 -> {
                LocationScreen(){
                    state += 1
                }
            }
            1 -> {
                CategoryScreen() {
                    state += 1
                }
            }
            2 -> {
                PriceRangeScreen() {
                    state += 1
                }
            }
            3 -> {
                RatingScreen() {
                    val activity = context as Activity
                    activity.startActivity(Intent(context, UserActivity::class.java))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column() {
            Text(
                text = "Choose Your Location",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        coffeeDrinks.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectedText = item
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
        Button(
            onClick = { onClick() },
            content = {
                Text(
                    text = "Next",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
        )
    }
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column() {
            Text(
                text = "Category of SME",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        Button(
            onClick = { onClick() },
            content = {
                Text(
                text = "Next",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                )
            },
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
        )
    }
}

@Composable
fun PriceRangeScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column() {
            Text(
                text = "Price Range",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        Button(
            onClick = { onClick() },
            content = {
                Text(
                    text = "Next",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
        )
    }
}

@Composable
fun RatingScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column() {
            Text(
                text = "Choose Rating",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        Button(
            onClick = { onClick() },
            content = {
                Text(
                    text = "Next",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SMEUsTheme {
        PreferenceScreen()
    }
}